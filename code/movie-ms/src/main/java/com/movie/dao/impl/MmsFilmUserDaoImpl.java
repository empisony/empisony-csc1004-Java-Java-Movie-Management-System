package com.movie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.movie.entity.MmsFilmUser;
import com.movie.service.MmsFilmUserService;
import com.movie.util.DbUtil;

public class MmsFilmUserDaoImpl implements MmsFilmUserService {

	@Override
	public int save(MmsFilmUser mmsFilmUser) {
		String sql = "insert into mms_film_user (film_id,user_id,score,comment) values (?,?,?,?)";
		DbUtil dbUtil = new DbUtil();
		int result = dbUtil.doUpdate(sql, mmsFilmUser.getFilmId(),mmsFilmUser.getUserId(),mmsFilmUser.getScore(),mmsFilmUser.getComment());
		return result;
	}

	@Override
	public ArrayList<Map<String, Object>> statRate() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT score type,COUNT(user_id) count FROM mms_film_user GROUP BY score";
		DbUtil dbUtil = new DbUtil();
		Connection conn = dbUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name",rs.getString("type"));
				map.put("value",rs.getInt("count"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Map<String, Object>> statAuRate() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT NAME type,COUNT(1) count FROM mms_film t1\r\n"
				+ "LEFT JOIN mms_film_user t2 on t1.id = t2.film_id GROUP BY id order by count desc";
		DbUtil dbUtil = new DbUtil();
		Connection conn = dbUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name",rs.getString("type"));
				map.put("value",rs.getInt("count"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
