package me.studi.thesis.VisioToGraph.file;

public enum Tools {
	INSTANCE;
	
	public String getEnding(String fileName) {
		String[] split = fileName.split("\\.");
		return split[split.length-1];
	}
	
}
