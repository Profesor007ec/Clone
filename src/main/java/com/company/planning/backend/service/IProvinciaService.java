package com.company.planning.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.planning.backend.model.Provincia;
import com.company.planning.backend.response.ProvinciaResponseRest;

public interface IProvinciaService {
	
	public ResponseEntity<ProvinciaResponseRest> save(Provincia provincia, Long zonaId);
	public ResponseEntity<ProvinciaResponseRest> searchById(Long id);
	public ResponseEntity<ProvinciaResponseRest> searchByName(String nombre);
	public ResponseEntity<ProvinciaResponseRest> deleteById(Long id);
	public ResponseEntity<ProvinciaResponseRest> search();
	public ResponseEntity<ProvinciaResponseRest> update(Provincia provincia, Long zonaId, Long id);
}
