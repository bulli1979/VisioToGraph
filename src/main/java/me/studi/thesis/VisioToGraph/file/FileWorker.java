package me.studi.thesis.VisioToGraph.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileWorker {
	File file;
	public FileWorker(File file) {
		this.file = file;
	}
	
	public String execute() throws IOException {
		String answer = "OK";
		File zip = new File("temp.zip");
		file.renameTo(zip);
		if(zip.exists()) {
			unzip(zip);
		}
		
		
		
		return answer;
	}

	private void unzip(File zip) throws IOException {
        File destDir = new File("temp");
        
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            System.out.println(newFile.getName());
            if(newFile.getName().equals("page1.xml")) {
            	
            }
            
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
	}
	
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
         
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
         
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
         
        return destFile;
    }
}
