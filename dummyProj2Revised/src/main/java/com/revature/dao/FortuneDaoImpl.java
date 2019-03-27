package com.revature.dao;

import java.util.List;

import com.revature.bean.Fortune;

public class FortuneDaoImpl implements FortuneDao{

	@Override //print from DB
	public List<Fortune> printAllFortunes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override //Get fortune and luckyNum from APIs
	public Fortune getRandomFortune() {
		// TODO Auto-generated method stub
		//implement random number generator (RNG)
		//run RNG twice to get random fortune and lucky number
		return null;
	}

	@Override
	public boolean deleteFortune(String fId, Integer uId) {
		// TODO Auto-generated method stub
		return false;
	}

}
