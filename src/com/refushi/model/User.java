package com.refushi.model;

public class User {

	
	private String id;
	private String last_name;
	private String email;
	private String password;
	private String first_name;
	private boolean active;
	
	
	public User(String id, String last_name, String email, String password,
			String first_name, boolean active) {
		super();
		this.id = id;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.first_name = first_name;
		this.active = active;
	}


	
	@Override
	public String toString() {
		return "User [id=" + id + ", last_name=" + last_name + ", email="
				+ email + ", password=" + password + ", first_name="
				+ first_name + ", active=" + active + "]";
	}


	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
		}
	}
