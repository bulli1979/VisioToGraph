package me.studi.thesis.VisioToGraph.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileWorker {
	private File file;
	private static final String destDir = System.getProperty("user.home") + File.separator + "temp";

	public FileWorker(File file) {
		this.file = file;
	}

	public String execute() throws IOException {
		String answer = "OK";
		File zip = new File("temp.zip");
		file.renameTo(zip);
		if (zip.exists()) {
			unzip();
		}

		return answer;
	}

	private void unzip() {
		File dir = new File(destDir);
		// create output directory if it doesn't exist
		if (!dir.exists())
			dir.mkdirs();
		FileInputStream fis;
		// buffer for read and write data to file
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(file);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				zis.closeEntry();
				ze = zis.getNextEntry();
				if(newFile.getName().equals("page1.xml")) {
					FileType.findWorker(Tools.INSTANCE.getEnding(file.getName())).readGraph(newFile);
				}
				
				
			}
			// close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
