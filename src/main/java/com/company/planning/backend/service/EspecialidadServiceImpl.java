package com.company.planning.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.planning.backend.dao.IEspecialidadDao;
import com.company.planning.backend.model.Especialidad;
import com.company.planning.backend.response.EspecialidadResponseRest;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

	@Autowired
	private IEspecialidadDao especialidadDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<EspecialidadResponseRest> search() {
		
		EspecialidadResponseRest response = new EspecialidadResponseRest();
		
		try {
			List<Especialidad> especialidad = (List<Especialidad>) especialidadDao.findAll();
			
			response.getEspecialidadResponse().setEspecialidad(especialidad);
			
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.OK);
	}

}
