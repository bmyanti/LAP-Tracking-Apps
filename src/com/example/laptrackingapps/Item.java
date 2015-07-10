package com.example.laptrackingapps;

import android.graphics.Bitmap;

public class Item {
	Bitmap image;
	String title;
	String id;
	
	
	public Item(String id, String title, Bitmap image) {
		super();
		this.image = image;
		this.title = title;
		this.id = id;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
}
