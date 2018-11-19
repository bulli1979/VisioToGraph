package me.studi.thesis.VisioToGraph.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import me.studi.thesis.VisioToGraph.file.Shape;

public class GraphWorker {
	private static final String ID = "key";
	private static final String WEIGHT = "weight";
	private static final String LABEL = "text";

	public GraphWorker() {
	}

	private Map<Integer, Vertex> verts = new HashMap<Integer, Vertex>();

	public Graph run(List<Shape> shapes) {
		Graph graph = new TinkerGraph();
		for (Shape shape : shapes) {
			if (shape.getFrom() == null) {
				createVertex(graph.addVertex(null), shape);
			}
		}

		for (Shape shape : shapes) {
			if (shape.getFrom() != null) {
				createEdge(graph, shape);
			}
		}
		return graph;
	}

	private void createEdge(Graph graph, Shape shape) {
		if (shape.getText() != null && shape.getFrom() != null && shape.getTo() != null) {
			if (verts.get(shape.getFrom()) != null && verts.get(shape.getTo()) != null) {
				Edge e = graph.addEdge(null, verts.get(shape.getFrom()), verts.get(shape.getTo()), shape.getText());
				e.setProperty(ID, shape.getId());
				e.setProperty(WEIGHT, shape.getWeight());
				e.setProperty(LABEL, shape.getText());
			}
		}
	}

	private void createVertex(Vertex vertex, Shape shape) {
		vertex.setProperty(ID, shape.getId());
		vertex.setProperty(WEIGHT, shape.getWeight());
		if (shape.getText() != null) {
			vertex.setProperty(LABEL, shape.getText());
		}
		verts.put(shape.getId(), vertex);
	}
}
