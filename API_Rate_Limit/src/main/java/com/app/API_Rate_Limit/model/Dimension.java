package com.app.API_Rate_Limit.model;

public class Dimension {
	 private int length;
	    private int breadth;
	    private String userId;
	    private int base;
	    private int height;
	    private int radius;
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public int getBreadth() {
			return breadth;
		}
		public void setBreadth(int breadth) {
			this.breadth = breadth;
		}
		public int getBase() {
			return base;
		}
		public void setBase(int base) {
			this.base = base;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public int getRadius() {
			return radius;
		}
		public void setRadius(int radius) {
			this.radius = radius;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}

}
