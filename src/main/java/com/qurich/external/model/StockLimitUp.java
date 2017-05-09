package com.qurich.external.model;

import java.util.Date;

public class StockLimitUp {

	private int id;
	private String stock;
	private double price_open;
	private double price_close;
	private double price_top;
	private double price_lower;
	private double price_rise; //涨幅
	private double price_riseper;//涨幅百分比
	private Date time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public double getPrice_open() {
		return price_open;
	}
	public void setPrice_open(double price_open) {
		this.price_open = price_open;
	}
	public double getPrice_close() {
		return price_close;
	}
	public void setPrice_close(double price_close) {
		this.price_close = price_close;
	}
	public double getPrice_top() {
		return price_top;
	}
	public void setPrice_top(double price_top) {
		this.price_top = price_top;
	}
	public double getPrice_lower() {
		return price_lower;
	}
	public void setPrice_lower(double price_lower) {
		this.price_lower = price_lower;
	}
	public double getPrice_rise() {
		return price_rise;
	}
	public void setPrice_rise(double price_rise) {
		this.price_rise = price_rise;
	}
	public double getPrice_riseper() {
		return price_riseper;
	}
	public void setPrice_riseper(double price_riseper) {
		this.price_riseper = price_riseper;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
