package com.revature.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.bean.Fortune;
import com.revature.exceptions.NoFortunesFoundException;
import com.revature.services.FortuneService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@Component
public class FortuneController {

	//@Autowired
	FortuneService fortuneService = new FortuneService();
	
	//get all fortunes by user id
	@GetMapping(path="/apifortunes", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Fortune>> getUsersFortunes(@RequestParam("id")Integer id) {
		List<Fortune> fortunes = fortuneService.getAllFortunesByUser(id);
		if(fortunes.size() == 0)
			throw new NoFortunesFoundException();
		
		else
			return new ResponseEntity<List<Fortune>>(fortunes, HttpStatus.OK);
	}
	
	//create fortune
	@PostMapping(path="/apifortunes", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Fortune> createFortune(@RequestBody Fortune newFortune) {
		boolean created = fortuneService.createFortune(newFortune);
		if(created)
			return new ResponseEntity<Fortune>(newFortune, HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	//delete a fortune
	@PostMapping(path="/deletefortune", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity deleteFortune(@RequestBody Fortune delete) {
		fortuneService.deleteFortune(delete.getId(), delete.getUser().getId());
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//delete all of users fortunes
	@PostMapping(path="/deleteall", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity deleteAllFortunes(@RequestBody Fortune delete) {
		fortuneService.deleteAllfortunes(delete.getUser().getId());
		return new ResponseEntity(HttpStatus.OK);
	}
}
