package com.company.planning.backend.service;

import org.springframework.http.ResponseEntity;


import com.company.planning.backend.response.ProvinciaResponseRest;

public interface IProvinciaService {
	
	public ResponseEntity<ProvinciaResponseRest> search();
	public ResponseEntity<ProvinciaResponseRest> searchById(Long id);
	
}
