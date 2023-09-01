package com.company.planning.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.planning.backend.dao.IProvinciaDao;
import com.company.planning.backend.model.Provincia;
import com.company.planning.backend.response.ProvinciaResponseRest;

@Service
public class ProvinciaServiceImpl implements IProvinciaService {
	
	@Autowired
	private IProvinciaDao provinciaDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProvinciaResponseRest> search() {
		
		ProvinciaResponseRest response = new ProvinciaResponseRest();
		
		try {
			List<Provincia> provincia = (List<Provincia>) provinciaDao.findAll();
			
			response.getProvinciaResponse().setProvincia(provincia);
			
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al consultar provincias");
			e.getStackTrace();
			return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.OK);
	}

	

}
