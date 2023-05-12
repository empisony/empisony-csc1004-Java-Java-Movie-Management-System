package com.movie.service;

import com.movie.entity.MmsUser;

public interface MmsUserService {

	/**
	 * 存储用户信息
	 * @param mmsUser 用户信息实体
	 */
	int save(MmsUser mmsUser);
	
	/**
	 * 查询用户信息
	 * @param userCode 用户名
	 * @param pwd 用户密码
	 */
	MmsUser getMmsUser(String userCode,String pwd);
}
