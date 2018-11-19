package me.studi.thesis.VisioToGraph.graph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.util.io.gml.GMLWriter;

public class GMLOutput {
	
	public GMLOutput() {}
	
	public void writeGML(Graph graph,String path) throws IOException {
		OutputStream out = new FileOutputStream(new File(path));
		GMLWriter.outputGraph(graph, out);
	}
}
