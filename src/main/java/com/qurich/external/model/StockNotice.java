package com.qurich.external.model;

import java.util.Date;

import cn.wangxs.excel.annotation.ExcelField;

public class StockNotice {

	private int id;
	@ExcelField(name = "股票代码")
	private String stock;
	@ExcelField(name = "股票名称")
	private String name;
	@ExcelField(name = "业绩变动")
	private String yjbd;
	@ExcelField(name = "业绩变动校验")
	private String yjbdjy;
	@ExcelField(name = "净利润上区间（万元）")
	private float yjbd_low;
	@ExcelField(name = "净利润下区间（万元）")
	private float yjbd_up;
	@ExcelField(name = "业绩变动幅度")
	private String yjbdfd;
	@ExcelField(name = "同比增长下区间")
	private float year_low;
	@ExcelField(name = "同比增长上区间")
	private float year_up;
	@ExcelField(name = "环比增长下区间")
	private double quarter_low;
	@ExcelField(name = "环比增长上区间")
	private double quarter_up;
	@ExcelField(name = "市盈率")
	private float syl;
	@ExcelField(name = "预期市盈率下区间")
	private float yqsyl_low;
	@ExcelField(name = "预期市盈率上区间")
	private float yqsyl_up;
	@ExcelField(name = "市盈率比")
	private double peratio;
	@ExcelField(name = "预期每股收益下区间")
	private float yqmgsy_low;
	@ExcelField(name = "预期每股收益上区间")
	private float yqmgsy_up;
	@ExcelField(name = "预告")
	private String nitoce;
	@ExcelField(name = "上年同期净利润（万元）")
	private double jrl_last;
	@ExcelField(name = "预告时间", format = "yyyy-MM-dd")
	private Date pday;
	private Date quarter_day;
	public String getYjbdjy() {
		return yjbdjy;
	}
	public void setYjbdjy(String yjbdjy) {
		this.yjbdjy = yjbdjy;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYjbd() {
		return yjbd;
	}
	public void setYjbd(String yjbd) {
		this.yjbd = yjbd;
	}
	public float getYjbd_low() {
		return yjbd_low;
	}
	public void setYjbd_low(float yjbd_low) {
		this.yjbd_low = yjbd_low;
	}
	public float getYjbd_up() {
		return yjbd_up;
	}
	public void setYjbd_up(float yjbd_up) {
		this.yjbd_up = yjbd_up;
	}
	public String getYjbdfd() {
		return yjbdfd;
	}
	public void setYjbdfd(String yjbdfd) {
		this.yjbdfd = yjbdfd;
	}
	public float getYear_low() {
		return year_low;
	}
	public void setYear_low(float year_low) {
		this.year_low = year_low;
	}
	public float getYear_up() {
		return year_up;
	}
	public void setYear_up(float year_up) {
		this.year_up = year_up;
	}
	public double getQuarter_low() {
		return quarter_low;
	}
	public void setQuarter_low(double quarter_low) {
		this.quarter_low = quarter_low;
	}
	public double getQuarter_up() {
		return quarter_up;
	}
	public void setQuarter_up(double quarter_up) {
		this.quarter_up = quarter_up;
	}
	public float getSyl() {
		return syl;
	}
	public void setSyl(float syl) {
		this.syl = syl;
	}
	public float getYqsyl_low() {
		return yqsyl_low;
	}
	public void setYqsyl_low(float yqsyl_low) {
		this.yqsyl_low = yqsyl_low;
	}
	public float getYqsyl_up() {
		return yqsyl_up;
	}
	public void setYqsyl_up(float yqsyl_up) {
		this.yqsyl_up = yqsyl_up;
	}
	public double getPeratio() {
		return peratio;
	}
	public void setPeratio(double peratio) {
		this.peratio = peratio;
	}
	public float getYqmgsy_low() {
		return yqmgsy_low;
	}
	public void setYqmgsy_low(float yqmgsy_low) {
		this.yqmgsy_low = yqmgsy_low;
	}
	public float getYqmgsy_up() {
		return yqmgsy_up;
	}
	public void setYqmgsy_up(float yqmgsy_up) {
		this.yqmgsy_up = yqmgsy_up;
	}
	public String getNitoce() {
		return nitoce;
	}
	public void setNitoce(String nitoce) {
		this.nitoce = nitoce;
	}
	public double getJrl_last() {
		return jrl_last;
	}
	public void setJrl_last(double jrl_last) {
		this.jrl_last = jrl_last;
	}
	public Date getPday() {
		return pday;
	}
	public void setPday(Date pday) {
		this.pday = pday;
	}
	public Date getQuarter_day() {
		return quarter_day;
	}
	public void setQuarter_day(Date quarter_day) {
		this.quarter_day = quarter_day;
	}
}
