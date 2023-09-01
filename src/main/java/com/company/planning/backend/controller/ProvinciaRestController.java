package com.company.planning.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.planning.backend.model.Provincia;
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
	
	@GetMapping("/provincias/{id}")
	public ResponseEntity<ProvinciaResponseRest> searchProvinciasById(@PathVariable Long id){
		ResponseEntity<ProvinciaResponseRest> response = serviceProvincia.searchById(id);
		return response;
	}
	
	@PutMapping("/provincias")
	public ResponseEntity<ProvinciaResponseRest> saveProvincias(@RequestBody Provincia provincia){
		ResponseEntity<ProvinciaResponseRest> response = serviceProvincia.save(provincia);
		return response;
	}
}
