package me.studi.thesis.VisioToGraph.file;

import java.io.File;

import com.tinkerpop.blueprints.Graph;
public interface XMLWorker {
	public void readGraph(File xml);
	public Graph getGraph();
}
