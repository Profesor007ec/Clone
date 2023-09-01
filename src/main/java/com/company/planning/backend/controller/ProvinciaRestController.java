package com.company.planning.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.planning.backend.response.ProvinciaResponseRest;
import com.company.planning.backend.service.IProvinciaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/planillaje")
public class ProvinciaRestController {
	
	@Autowired
	private IProvinciaService serviceProvincia;
	
	@GetMapping("/provincias")
	public ResponseEntity<ProvinciaResponseRest> searchProvincias(){
		ResponseEntity<ProvinciaResponseRest> response = serviceProvincia.search();
		return response;
	}
	
}
