package chap4;

public class InfraredRaySensor {
	private String name;
	private boolean objectRounded;
	public InfraredRaySensor(String name) {
		this.name = name;
	}
	public void foundObject() {
		this.objectRounded = true;
	}
	public String getName() {
		return name;
	}
	public boolean isobjectRounded() {
		return objectRounded;
	}
	@Override
	public String toString() {
		return "InfraredRaySensor [name=" + name + ", objectRounded=" + objectRounded + "]";
	}
	
}
