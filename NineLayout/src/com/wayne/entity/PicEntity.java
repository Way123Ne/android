package com.wayne.entity;

public class PicEntity {
	public String title;
	public int imageId;

	public PicEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PicEntity(String title, int imageId) {
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}
