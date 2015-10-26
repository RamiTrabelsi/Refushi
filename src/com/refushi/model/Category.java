package com.refushi.model;


public class Category {
	
	private String id ;
	private String name ;
	private String photo ;
	

	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", photo=" + photo
				+ "]";
	}



	public Category(String id, String name, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public Category() {
		super();
	}
	
	
	
	}

