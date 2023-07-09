/**
 * 
 */
package com.app.API_Rate_Limit.model;

/**
 * @author CQ927DW
 *
 */

public class Perimeter {
	private String shape;
    private Double perimeter;
    
    public Perimeter(String type, double value)
    {
    	System.out.println("Perimeter");
    	shape=type;
    	perimeter=value;
    }
    public Perimeter()
    {
    }
    
    
    public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public Double getPerimeter() {
		return perimeter;
	}
	public void setPerimeter(Double perimeter) {
		this.perimeter = perimeter;
	}

}
