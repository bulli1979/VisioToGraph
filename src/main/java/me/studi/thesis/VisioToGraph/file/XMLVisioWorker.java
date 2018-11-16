package me.studi.thesis.VisioToGraph.file;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLVisioWorker implements XMLWorker {
	private static final String NAMEU = "NameU";
	private static final String MASTER = "Master";
	private static final String TEXT = "Text";
	private static final String SHAPE = "Shape";
	private static final Object CELL = "Cell";
	private static final String BEGINN_TRIGGER = "BegTrigger";
	private static final Object END_TRIGGER = "EndTrigger";
	private static final String N = "N";
	private static final String F = "F";


	public XMLVisioWorker() {}

	
	private List<Shape> shapeList;

	public void readGraph(File xml) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName(SHAPE);
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				addSpapeToList(node);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void addSpapeToList(Node node) {
		try {
			Element element = (Element) node;
			String uName = element.getAttribute(NAMEU).toString();
			int master = Integer.parseInt(element.getAttribute(MASTER));
			NodeList childs = node.getChildNodes();
			Shape shape = new Shape();
			for (int i = 0; i < childs.getLength(); i++) {
				Element child = (Element) childs.item(i);
				fillChild(shape,child);
			}
			ShapeType shapeType = ShapeType.getByMasterOrName(master, uName);
			
			shape.setId(master);
			if (shapeType != null) {
				shape.setWeight(shapeType.getWeight());
				shapeList.add(shape);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillChild(Shape shape,Element child) {
		if (child.getNodeName().equals(TEXT)) {
			shape.setText(child.getNodeValue());
		}
		if (child.getNodeName().equals(CELL)) {
			if(child.getAttribute(N).equals(BEGINN_TRIGGER)) {
				shape.setFrom(getValueFromChild(child.getAttribute(F)));
			}
			if(child.getAttribute(N).equals(END_TRIGGER)) {
				shape.setTo(getValueFromChild(child.getAttribute(F)));
			}
		}
	}

	private int getValueFromChild(String attribute) {
		return Integer.parseInt(attribute.split("\\.")[1].split("!")[0]);
	}
}
