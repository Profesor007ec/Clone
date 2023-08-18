package com.company.planning.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.planning.backend.model.Especialidad;
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
	
	@GetMapping("/especialidades/{id}")
	public ResponseEntity<EspecialidadResponseRest> searchEspecialidadesById(@PathVariable Long id){
		ResponseEntity<EspecialidadResponseRest> response = service.searchById(id);
		return response;
	}
	
	@PostMapping("/especialidades")
	public ResponseEntity<EspecialidadResponseRest> save(@RequestBody Especialidad especialidad){
		ResponseEntity<EspecialidadResponseRest> response = service.save(especialidad);
		return response;
	}
	
	@PutMapping("/especialidades/{id}")
	public ResponseEntity<EspecialidadResponseRest> update(@RequestBody Especialidad especialidad, @PathVariable Long id){
		ResponseEntity<EspecialidadResponseRest> response = service.update(especialidad, id);
		return response;
	}

}
