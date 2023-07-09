/**
 * 
 */
package com.app.API_Rate_Limit.model;

/**
 * @author CQ927DW
 *
 */
public class Area {
	private String shape;
	private double area;
	
	public Area() {}
	public Area(String shape, double val)
	{
		System.out.println("Area");
		this.shape=shape;
		area=val;
	
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}

	
	

}
