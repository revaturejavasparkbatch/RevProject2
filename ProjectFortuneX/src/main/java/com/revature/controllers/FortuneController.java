package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.bean.Fortune;
import com.revature.dao.FortuneDao;
import com.revature.dao.FortuneDaoImpl;
import com.revature.exceptions.NoFortunesFoundException;

@Controller
public class FortuneController {

	FortuneDao fd = new FortuneDaoImpl();
	
	@GetMapping("/apifortunes")
	@ResponseBody
	public List<Fortune> getUsersFortunes(@RequestParam("id")Integer id){
		List<Fortune> fortunes = fd.getAllFortunesByUser(id);
		if(fortunes.size() == 0)
			throw new NoFortunesFoundException();
		
		else
			return fortunes;
	}
}
