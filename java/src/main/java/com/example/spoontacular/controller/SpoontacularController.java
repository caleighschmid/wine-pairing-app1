package com.example.spoontacular.controller;

import java.util.List;

import com.example.spoontacular.model.WineRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.spoontacular.model.Gif;
import com.example.spoontacular.services.SpoontacularService;

@RestController
public class SpoontacularController {

	@Autowired
	SpoontacularService spoontacularService;
	@CrossOrigin
	@RequestMapping(path="/getPairing", method=RequestMethod.GET)
	public WineRecommendation test(@RequestParam String query) {

		return spoontacularService.getWinePairing(query);
		
	}
	
}
