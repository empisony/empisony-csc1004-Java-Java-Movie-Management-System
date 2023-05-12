package com.movie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.movie.dao.MmsFilmDao;
import com.movie.entity.MmsFilm;
import com.movie.util.DbUtil;

public class MmsFilmDaoImpl implements MmsFilmDao {

	@Override
	public int save(MmsFilm mmsFilm) {
		String sql = "insert into mms_film (id,name,rate,type,description,poster,movie) values (?,?,?,?,?,?,?)";
		DbUtil dbUtil = new DbUtil();
		int result = dbUtil.doUpdate(sql, mmsFilm.getId(),mmsFilm.getName(),mmsFilm.getRate(),mmsFilm.getType()
				,mmsFilm.getDescription(),mmsFilm.getPoster(),mmsFilm.getMovie());
		return result;
	}

	@Override
	public int delete(String id) {
		String sql = "delete from mms_film where id = ?";
		DbUtil dbUtil = new DbUtil();
		int result = dbUtil.doUpdate(sql, id);
		return result;
	}

	@Override
	public ArrayList<MmsFilm> getMmsFilm(String id, String value) {
		ArrayList<MmsFilm> list = new ArrayList<MmsFilm>();
		String sql = "";
		if(StringUtils.isBlank(id)) {
			sql = "select id,name,(SELECT AVG(score) FROM mms_film_user WHERE film_id = id) rate,(SELECT count(score) FROM mms_film_user WHERE film_id = id) au_rate,type,description,poster from mms_film";
		} else {
			sql = "select id,name,(SELECT AVG(score) FROM mms_film_user WHERE film_id = id) rate,(SELECT count(score) FROM mms_film_user WHERE film_id = id) au_rate,type,description,poster from mms_film where id = ? and name like '%?%'";
		}
		DbUtil dbUtil = new DbUtil();
		Connection conn = dbUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if(StringUtils.isNotBlank(id)) {
				ps.setString(1, id);
				ps.setString(2, value);	
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MmsFilm mmsFilm = new MmsFilm();
				mmsFilm.setId(rs.getString("id"));
				mmsFilm.setName(rs.getString("name"));
				mmsFilm.setRate(rs.getString("rate"));
				mmsFilm.setAuRate(rs.getString("au_rate"));
				mmsFilm.setType(rs.getString("type"));
				mmsFilm.setDescription(rs.getString("description"));
				mmsFilm.setPoster(rs.getString("poster"));
				list.add(mmsFilm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Map<String, Object>> statMType() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT type,COUNT(type) count FROM mms_film GROUP BY type";
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
