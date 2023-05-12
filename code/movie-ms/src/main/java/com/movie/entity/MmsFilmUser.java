package com.movie.entity;


/**
 * 
 * @desc 电影用户信息
 *
 */
public class MmsFilmUser {

	private String filmId; // 影片主键
	private String userId; // 用户主键
	private String score; // 评分
	private String comment; // 评论
	
	public MmsFilmUser() {
	}
	
	public MmsFilmUser(String filmId, String userId, String score, String comment) {
		this.filmId = filmId;
		this.userId = userId;
		this.score = score;
		this.comment = comment;
	}

	public String getFilmId() {
		return filmId;
	}
	public void setFilmId(String filmId) {
		this.filmId = filmId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
