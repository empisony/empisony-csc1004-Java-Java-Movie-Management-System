package com.movie.service.impl;

import com.movie.dao.impl.MmsUserDaoImpl;
import com.movie.entity.MmsUser;
import com.movie.service.MmsUserService;

public class MmsUserServiceImpl implements MmsUserService {

	@Override
	public int save(MmsUser mmsUser) {
		MmsUserDaoImpl mmsUserDao = new MmsUserDaoImpl();
		return mmsUserDao.save(mmsUser);
	}

	/**
	 * 查询用户信息
	 * @param userCode 用户名
	 * @param pwd 用户密码
	 */
	public MmsUser getMmsUser(String userCode,String pwd) {
		MmsUserDaoImpl mmsUserDao = new MmsUserDaoImpl();
		return mmsUserDao.getMmsUser(userCode, pwd);
	}

}
