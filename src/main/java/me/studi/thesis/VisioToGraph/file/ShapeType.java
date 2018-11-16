package me.studi.thesis.VisioToGraph.file;

public enum ShapeType {
	INITIAL("Initial node",0), 
	ACTION("Action",1), 
	CONNECTOR("Dynamic connector",1), 
	Decision("Decision",3);

	private String uName;
	private int master = 0;
	private int weight;
	private ShapeType(String uname,int weight) {
		this.uName = uname;
		this.weight = weight;
	}

	public static ShapeType getByMasterOrName(int master, String uName) {
		
		for (ShapeType st : ShapeType.values()) {
			if (uName != null && uName.equals(st.uName) || st.master == master) {
				st.master = master;
				return st;
			}
		}
		System.out.println(master + " " + uName);
		return null;
	}
	
	public int getWeight() {
		return this.weight;
	}

}
