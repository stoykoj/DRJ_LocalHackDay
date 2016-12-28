package com.julia.drj_localhackday2016;

public class Users {
	private String userName;
	private String name;
	private String img;
	
	public Users( String userName, String name, String img ) {
		this.userName = userName;
		this.name = name;
		this.img = img;
	}
	
	public String getUserName() { return userName; }
	public String getName() { return name; }
	public String getImg() { return img; }
	public void setUserName( String userName ) { this.userName = userName; }
	public void setName( String name ) { this.name = name; }
	public void setImg( String img ) { this.img = img; }
}
