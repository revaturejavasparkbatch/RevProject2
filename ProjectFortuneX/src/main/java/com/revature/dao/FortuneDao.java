package com.revature.dao;

import java.util.List;

import com.revature.bean.Fortune;

public interface FortuneDao {
	public List<Fortune> getAllFortunesByUser(Integer uId);
	public boolean createFortune(Fortune fortune);
	public void deleteFortune(String fId, Integer uId);
	public void deleteAllFortunes(Integer uId);
}
