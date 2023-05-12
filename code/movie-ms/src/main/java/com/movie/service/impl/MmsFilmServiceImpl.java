package com.movie.service.impl;

import java.util.ArrayList;
import java.util.Map;

import com.movie.dao.impl.MmsFilmDaoImpl;
import com.movie.entity.MmsFilm;
import com.movie.service.MmsFilmService;

public class MmsFilmServiceImpl implements MmsFilmService {
	
	MmsFilmDaoImpl mmsFilmDao = new MmsFilmDaoImpl();

	@Override
	public int save(MmsFilm mmsFilm) {
		int result = mmsFilmDao.save(mmsFilm);
		return result;
	}

	@Override
	public int delete(String id) {
		int result = mmsFilmDao.delete(id);
		return result;
	}

	@Override
	public ArrayList<MmsFilm> getMmsFilm(String id, String value) {
		ArrayList<MmsFilm> mmsFilm = mmsFilmDao.getMmsFilm(id, value);
		return mmsFilm;
	}

	@Override
	public ArrayList<Map<String, Object>> statMType() {
		return mmsFilmDao.statMType();
	}

}
