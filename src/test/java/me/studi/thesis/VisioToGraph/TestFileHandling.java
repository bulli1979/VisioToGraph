package me.studi.thesis.VisioToGraph;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import me.studi.thesis.VisioToGraph.file.FileWorker;

public class TestFileHandling {

	@Test
	public void testFileHanlder() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("auswahlprozess.vsdx").getFile());
		if (file != null) {
			FileWorker fw = new FileWorker(file);
			fw.execute();
			System.out.println("jippi");
		}
	}
}
