package com.movie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.movie.dao.MmsUserDao;
import com.movie.entity.MmsUser;
import com.movie.util.DbUtil;

public class MmsUserDaoImpl implements MmsUserDao {

	@Override
	public int save(MmsUser mmsUser) {
		String sql = "insert into mms_user (user_code,user_name,nick_name,age,sex,address,password,is_admin) values (?,?,?,?,?,?,?,?)";
		DbUtil dbUtil = new DbUtil();
		int result = dbUtil.doUpdate(sql, mmsUser.getUserCode(),mmsUser.getUserName(),mmsUser.getNickName()
				,mmsUser.getAge(),mmsUser.getSex(),mmsUser.getAddress(),mmsUser.getPassword(),"0");
		return result;
	}

	/**
	 * 查询用户信息
	 * @param userCode 用户名
	 * @param pwd 用户密码
	 */
	public MmsUser getMmsUser(String userCode,String pwd) {
		MmsUser mmsUser = new MmsUser();
		String sql = "";
		if(StringUtils.isNotBlank(pwd)) {
			sql = "select user_code,user_name,nick_name,age,sex,address,password,is_admin from mms_user where user_code = ? and password = ?";	
		} else {
			sql = "select user_code,user_name,nick_name,age,sex,address,password,is_admin from mms_user where user_code = ?";	
		}
		DbUtil dbUtil = new DbUtil();
		Connection conn = dbUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userCode);
			if(StringUtils.isNotBlank(pwd)) {
				ps.setString(2, pwd);	
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				mmsUser.setUserCode(rs.getString("user_code"));
				mmsUser.setUserName(rs.getString("user_name"));
				mmsUser.setNickName(rs.getString("nick_name"));
				mmsUser.setPassword(rs.getString("password"));
				mmsUser.setAge(rs.getString("age"));
				mmsUser.setSex(rs.getString("sex"));
				mmsUser.setAddress(rs.getString("address"));
				mmsUser.setIsAdmin(rs.getString("is_admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mmsUser;
	}

}
