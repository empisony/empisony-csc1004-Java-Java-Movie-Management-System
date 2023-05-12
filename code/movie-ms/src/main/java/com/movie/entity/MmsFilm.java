package com.movie.entity;


/**
 * 
 * @desc 电影用户信息
 *
 */
public class MmsFilm {

	private String id; // 主键
	private String name; // 电影名称
	private String rate; // 评级
	private String type; // 电影类型
	private String description; // 电影介绍
	private String poster; // 电影海报
	private String movie; // 视频
	private String auRate; // 收视率
	
	public MmsFilm(){
		
	}

	public MmsFilm(String id, String name, String rate, String type, String description, String poster, String movie) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
		this.type = type;
		this.description = description;
		this.poster = poster;
		this.movie = movie;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getAuRate() {
		return auRate;
	}

	public void setAuRate(String auRate) {
		this.auRate = auRate;
	}

}
