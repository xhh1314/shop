package shop.bean;

import java.util.List;

public class Category {
	private String uuid;
	private String name;
	private String description;
	private List<Subdivide> subdivide;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Subdivide> getSubdivide() {
		return subdivide;
	}
	public void setSubdivide(List<Subdivide> subdivide) {
		this.subdivide = subdivide;
	}
	

}
