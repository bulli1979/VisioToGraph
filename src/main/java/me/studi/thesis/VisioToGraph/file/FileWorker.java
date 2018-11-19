package me.studi.thesis.VisioToGraph.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.tinkerpop.blueprints.Graph;

import me.studi.thesis.VisioToGraph.graph.GMLOutput;

public class FileWorker {
	private File file;
	private static final String DEST_DIR = System.getProperty("user.home") + File.separator + "temp";

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
		File dir = new File(DEST_DIR);
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
				File newFile = new File(DEST_DIR + File.separator + fileName);
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
					XMLWorker worker = FileType.findWorker(Tools.INSTANCE.getEnding(file.getName()));
					worker.readGraph(newFile);
					Graph graph = worker.getGraph();
					GMLOutput gmlop = new GMLOutput();
					gmlop.writeGML(graph, DEST_DIR+File.separator + "graph.gml");
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
