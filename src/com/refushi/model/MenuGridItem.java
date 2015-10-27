package com.refushi.model;


public class MenuGridItem {
	
	private String menuText ;
	private int menuDrawable ;
	
	public MenuGridItem() {
		super();
	}
	
	public MenuGridItem(String menuText, int menuDrawable) {
		super();
		this.menuText = menuText;
		this.menuDrawable = menuDrawable;
	}

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}

	public int getMenuDrawable() {
		return menuDrawable;
	}

	public void setMenuDrawable(int menuDrawable) {
		this.menuDrawable = menuDrawable;
	}
	
	

}
