package com.movie.service;

import java.util.ArrayList;
import java.util.Map;

import com.movie.entity.MmsFilm;

public interface MmsFilmService {

	/**
	 * 存储电影信息
	 * @param MmsFilm 电影信息实体
	 */
	int save(MmsFilm MmsFilm);
	
	
	/**
	 * 删除电影信息
	 * @param id 主键
	 */
	int delete(String id);
	
	/**
	 * 查询电影用户信息
	 * @param id 主键
	 * @param value 查询值
	 */
	ArrayList<MmsFilm> getMmsFilm(String id, String value);
	
	/**
	 * 查询电影分布
	 * @return
	 */
	ArrayList<Map<String,Object>> statMType();
}
