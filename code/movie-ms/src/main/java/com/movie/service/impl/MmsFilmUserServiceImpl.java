package com.movie.service.impl;

import java.util.ArrayList;
import java.util.Map;

import com.movie.dao.impl.MmsFilmUserDaoImpl;
import com.movie.entity.MmsFilmUser;
import com.movie.service.MmsFilmUserService;

public class MmsFilmUserServiceImpl implements MmsFilmUserService {
	
	
	MmsFilmUserDaoImpl mmsFilmUserDao = new MmsFilmUserDaoImpl();

	@Override
	public int save(MmsFilmUser mmsFilmUser) {
		return mmsFilmUserDao.save(mmsFilmUser);
	}

	@Override
	public ArrayList<Map<String, Object>> statRate() {
		return mmsFilmUserDao.statRate();
	}

	@Override
	public ArrayList<Map<String, Object>> statAuRate() {
		return mmsFilmUserDao.statAuRate();
	}

}
