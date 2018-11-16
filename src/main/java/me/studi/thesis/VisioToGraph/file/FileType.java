package me.studi.thesis.VisioToGraph.file;

import java.security.InvalidParameterException;

public enum FileType {
	//here is the point for new File Types
	VSDX(new XMLVisioWorker());
	
	
	private XMLWorker worker;
	
	private FileType(XMLWorker worker) {
		this.worker = worker;
	}
	
	public static XMLWorker findWorker(String ending) {
		for(FileType ft : FileType.values()) {
			if(ft.toString().toLowerCase().equals(ending.toLowerCase())) {
				return ft.worker;
			}
		}
		throw new InvalidParameterException();
	}
	
	
	
}
