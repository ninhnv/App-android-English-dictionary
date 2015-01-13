package com.ninh.tuvungtienganh;

public class english {
	String _id;
	String name;
	String category;
	String read;
	String vicontent;
	String alpha;
	String isreads;
	String isure;
	String isnext;
	
	public english(String _id,String name,String category,String read,String vicontent,String alpha,String isreads,String isure,String isnext)
	{
		this._id = _id;
		this.name = name;
		this.category= category;
		this.read = read;
		this.vicontent = vicontent;
		this.alpha = alpha;
		this.isreads = isreads;
		this.isure = isure;
		this.isnext = isnext;
		
	}
	public String getID() {
		return _id;
	}
	public String getName() {
		return name;
	}
	public String getCat() {
		return category;
	}
	public String getRead() {
		return read;
	}
	public String getVicontent() {
		return vicontent;
	}
	public String getAlpha() {
		return alpha;
	}
	public String getIsreads() {
		return isreads;
	}
	public String getIsure() {
		return isure;
	}
	public String getIsnext() {
		return isnext;
	}
}
