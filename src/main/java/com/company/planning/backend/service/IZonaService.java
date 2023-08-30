package com.company.planning.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.planning.backend.response.ZonaResponseRest;

public interface IZonaService {
	
	public ResponseEntity<ZonaResponseRest> search();

}
