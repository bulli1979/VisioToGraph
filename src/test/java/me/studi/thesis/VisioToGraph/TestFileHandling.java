package me.studi.thesis.VisioToGraph;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import me.studi.thesis.VisioToGraph.file.FileType;
import me.studi.thesis.VisioToGraph.file.FileWorker;
import me.studi.thesis.VisioToGraph.file.Tools;
import me.studi.thesis.VisioToGraph.file.XMLWorker;

public class TestFileHandling {

	@Test
	public void testFileHanlder() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("auswahlprozess.vsdx").getFile());
		if (file != null) {
			FileWorker fw = new FileWorker(file);
			fw.execute();
		}
	}
	@Test
	public void TestTypeFinding(){
		String fileName = "auswahlprozess.vsdx";
		String ending = Tools.INSTANCE.getEnding(fileName);
		assertTrue(ending.equals("vsdx"));
		XMLWorker worker = FileType.findWorker(ending);
		assertTrue(worker.getClass().getName().equals("me.studi.thesis.VisioToGraph.file.XMLVisioWorker"));
		
	}
}
