package com.qurich.external.model;

import java.util.Date;

import cn.wangxs.excel.annotation.ExcelField;

public class CopyOfStockPerformance {

	private int id;
	@ExcelField(name = "股票代码")
	private String stock;
	@ExcelField(name = "股票名称")
	private String name;
	@ExcelField(name = "每股收益（元）")
	private float mgsy;
	@ExcelField(name = "营业收入（万元）")
	private double yysr;
	@ExcelField(name = "同比增长")
	private float yysr_year;
	@ExcelField(name = "环比增长")
	private float yysr_quarter;
	@ExcelField(name = "去年同期净利润（万元）")
	private double jrl_last;
	@ExcelField(name = "业绩变动下限")
	private float yjbd_low;
	@ExcelField(name = "业绩变动上限")
	private float yjbd_up;
	@ExcelField(name = "业绩变动")
	private String yjbd;
	@ExcelField(name = "预告")
	private String nitoce;
	@ExcelField(name = "净利润（万元）")
	private double jrl;
	@ExcelField(name = "同比增长")
	private float jrl_year;
	@ExcelField(name = "环比增长")
	private float jrl_quarter;
	@ExcelField(name = "每股净资产（元）")
	private float mgjzc;
	@ExcelField(name = "净资产收益率")
	private float jzcsyl;
	@ExcelField(name = "每股现金流量（元）")
	private float mgxjll;
	@ExcelField(name = "销售毛利率")
	private float mlv;
	@ExcelField(name = "现价市盈率")
	private double pe;
	@ExcelField(name = "预期市盈率")
	private double npe;
	@ExcelField(name = "市盈率比")
	private double peratio;
	@ExcelField(name = "预告时间", format = "yyyy-MM-dd")
	private Date public_day;
	private Date quarter_day;
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
	public float getMgsy() {
		return mgsy;
	}
	public void setMgsy(float mgsy) {
		this.mgsy = mgsy;
	}
	public double getYysr() {
		return yysr;
	}
	public void setYysr(double yysr) {
		this.yysr = yysr;
	}
	public float getYysr_year() {
		return yysr_year;
	}
	public void setYysr_year(float yysr_year) {
		this.yysr_year = yysr_year;
	}
	public float getYysr_quarter() {
		return yysr_quarter;
	}
	public void setYysr_quarter(float yysr_quarter) {
		this.yysr_quarter = yysr_quarter;
	}
	public double getJrl_last() {
		return jrl_last;
	}
	public void setJrl_last(double jrl_last) {
		this.jrl_last = jrl_last;
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
	public String getYjbd() {
		return yjbd;
	}
	public void setYjbd(String yjbd) {
		this.yjbd = yjbd;
	}
	public String getNitoce() {
		return nitoce;
	}
	public void setNitoce(String nitoce) {
		this.nitoce = nitoce;
	}
	public double getJrl() {
		return jrl;
	}
	public void setJrl(double jrl) {
		this.jrl = jrl;
	}
	public float getJrl_year() {
		return jrl_year;
	}
	public void setJrl_year(float jrl_year) {
		this.jrl_year = jrl_year;
	}
	public float getJrl_quarter() {
		return jrl_quarter;
	}
	public void setJrl_quarter(float jrl_quarter) {
		this.jrl_quarter = jrl_quarter;
	}
	public float getMgjzc() {
		return mgjzc;
	}
	public void setMgjzc(float mgjzc) {
		this.mgjzc = mgjzc;
	}
	public float getJzcsyl() {
		return jzcsyl;
	}
	public void setJzcsyl(float jzcsyl) {
		this.jzcsyl = jzcsyl;
	}
	public float getMgxjll() {
		return mgxjll;
	}
	public void setMgxjll(float mgxjll) {
		this.mgxjll = mgxjll;
	}
	public float getMlv() {
		return mlv;
	}
	public void setMlv(float mlv) {
		this.mlv = mlv;
	}
	public double getPe() {
		return pe;
	}
	public void setPe(double pe) {
		this.pe = pe;
	}
	public double getNpe() {
		return npe;
	}
	public void setNpe(double npe) {
		this.npe = npe;
	}
	public double getPeratio() {
		return peratio;
	}
	public void setPeratio(double peratio) {
		this.peratio = peratio;
	}
	public Date getPublic_day() {
		return public_day;
	}
	public void setPublic_day(Date public_day) {
		this.public_day = public_day;
	}
	public Date getQuarter_day() {
		return quarter_day;
	}
	public void setQuarter_day(Date quarter_day) {
		this.quarter_day = quarter_day;
	}
	
}
