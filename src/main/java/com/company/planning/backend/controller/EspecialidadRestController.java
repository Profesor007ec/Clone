package com.company.planning.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.planning.backend.response.EspecialidadResponseRest;
import com.company.planning.backend.service.IEspecialidadService;

@RestController
@RequestMapping("/api/planillaje")
public class EspecialidadRestController {
	
	@Autowired
	private IEspecialidadService service;
	
	@GetMapping("/especialidades")
	public ResponseEntity<EspecialidadResponseRest> searchEspecialidades(){
		ResponseEntity<EspecialidadResponseRest> response = service.search();
		return response;
	}

}
