package com.company.planning.backend.controller;

import java.io.IOException;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.planning.backend.model.Provincia;
import com.company.planning.backend.response.ProvinciaResponseRest;
import com.company.planning.backend.service.IProvinciaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/planillaje")
public class ProvinciaRestController {
	
	private IProvinciaService provinciaService;
	
	public ProvinciaRestController(IProvinciaService provinciaService) {
		super();
		this.provinciaService = provinciaService;
	}

	@GetMapping("/provincias")
	public ResponseEntity<ProvinciaResponseRest> searchProvincias(){
		ResponseEntity<ProvinciaResponseRest> response = provinciaService.search();
		return response;
	}
	
	@GetMapping("/provincias/{id}")
	public ResponseEntity<ProvinciaResponseRest> searchProvinciasById(@PathVariable Long id){
		ResponseEntity<ProvinciaResponseRest> response = provinciaService.searchById(id);
		return response;
	}
	
	@PutMapping("/provincias")
	public ResponseEntity<ProvinciaResponseRest> saveProvincias(
			@RequestParam("codigo") String codigo,
			@RequestParam("nombre") String nombre,
			@RequestParam("estado") Boolean estado,
			@RequestParam("zonaId") Long zonaId) throws IOException
	{
		Provincia provincia = new Provincia();
		provincia.setCodigo(codigo);
		provincia.setNombre(nombre);
		provincia.setEstado(estado);
		ResponseEntity<ProvinciaResponseRest> response = provinciaService.save(provincia, zonaId);
		return response;
	}
}
