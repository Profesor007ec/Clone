package com.company.planning.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.planning.backend.model.Zona;
import com.company.planning.backend.response.ZonaResponseRest;

public interface IZonaService {
	
	public ResponseEntity<ZonaResponseRest> search();
	public ResponseEntity<ZonaResponseRest> searchById(Long id);
	public ResponseEntity<ZonaResponseRest> save(Zona zona);
	public ResponseEntity<ZonaResponseRest> update(Zona zona, Long id);
	public ResponseEntity<ZonaResponseRest> deleteById(Long id);
}
