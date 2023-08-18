package com.company.planning.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.planning.backend.model.Especialidad;
import com.company.planning.backend.response.EspecialidadResponseRest;

public interface IEspecialidadService {
	
	public ResponseEntity<EspecialidadResponseRest> search();
	public ResponseEntity<EspecialidadResponseRest> searchById(Long id);
	public ResponseEntity<EspecialidadResponseRest> save(Especialidad especialidad);
	public ResponseEntity<EspecialidadResponseRest> update(Especialidad especialidad, Long id);

}
