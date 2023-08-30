package com.company.planning.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.planning.backend.response.ZonaResponseRest;
import com.company.planning.backend.service.IZonaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/planillaje")
public class ZonaRestController {
	
	@Autowired
	private IZonaService service;
	
	@GetMapping("/zonas")
	public ResponseEntity<ZonaResponseRest> searchZonas(){
		ResponseEntity<ZonaResponseRest> response = service.search();
		return response;
	}

}
