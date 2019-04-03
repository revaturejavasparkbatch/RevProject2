package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.bean.Fortune;
import com.revature.dao.FortuneDaoImpl;

@Service
public class FortuneService {
	
	FortuneDaoImpl fortuneDaoImpl = new FortuneDaoImpl();
	
	public List<Fortune> getAllFortunesByUser(Integer uId){
		return fortuneDaoImpl.getAllFortunesByUser(uId);
	}
	
	public boolean createFortune(Fortune fortune) {
		return fortuneDaoImpl.createFortune(fortune);
	}
	
	public void deleteFortune(String fId, Integer uId) {
		fortuneDaoImpl.deleteFortune(fId, uId);
	}
	
	public void deleteAllfortunes(Integer uId) {
		fortuneDaoImpl.deleteAllFortunes(uId);
	}
}
