package com.movie.dao;

import java.util.ArrayList;
import java.util.Map;

import com.movie.entity.MmsFilmUser;

public interface MmsFilmUserDao {

	int save(MmsFilmUser mmsFilmUser);
	
	/**
	 * 查询电影评级分布
	 * @return
	 */
	ArrayList<Map<String,Object>> statRate();
	
	/**
	 * 查询收视率
	 * @return
	 */
	ArrayList<Map<String,Object>> statAuRate();
}
