package com.revature.dao;

import java.util.List;

import com.revature.bean.Fortune;

public interface FortuneDao {
	public List<Fortune> printAllFortunes();
	public Fortune getRandomFortune(); //get fortune from api, get fortune and lucky number in same method then display
	public boolean deleteFortune(String fId, Integer uId);
}
