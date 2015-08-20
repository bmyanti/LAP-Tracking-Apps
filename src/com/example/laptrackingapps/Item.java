//this class is used as model in class listanak and customgridviewAdapter to put the model into gridarray to display image and name of children

package com.example.laptrackingapps;

import android.graphics.Bitmap;

public class Item {
	Bitmap image; //bitmap image child
	String title; //name of child
	String id; //id of child
	
	
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
