package model;

public class IAType {
	private int id;
	private int level;
	
	public IAType(int id, int level) {
		this.id = id;
		this.level = level;
	}
	
	public IAType(int level) {
		this.id = -1;
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}
}
