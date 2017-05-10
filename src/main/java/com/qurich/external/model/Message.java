package com.qurich.external.model;

import java.util.Date;

public class Message{
	
	private int id;
	private String title;
	private String href;
	private String content;
	private String summary;
	private int from; //0财联社，1上证，2大智慧，3东方财富,4 同花顺 24小时,5东方财富24小时直播，6华尔街见闻,7云财经
	private Date create_time; //创建时间
	private int is_del;
	private int comment; //评论数
	private int view;  //查看数
	private Date time;//财联社网站发布的时间
	private String stock; //推荐的股票
	private int lucene; //是否索引
	private String bull_category;
	private int status; //0无标示，1 A股
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", href=" + href
				+ ", content=" + content + ", from=" + from + ", create_time="
				+ create_time + ", is_del=" + is_del + ", comment=" + comment
				+ ", view=" + view + ", time=" + time + "]";
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBull_category() {
		return bull_category;
	}

	public void setBull_category(String bull_category) {
		this.bull_category = bull_category;
	}
	public int getLucene() {
		return lucene;
	}

	public void setLucene(int lucene) {
		this.lucene = lucene;
	}
	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getIs_del() {
		return is_del;
	}

	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
