package com.company.planning.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.planning.backend.dao.IZonaDao;
import com.company.planning.backend.model.Zona;
import com.company.planning.backend.response.ZonaResponseRest;

@Service
public class ZonaServiceImpl implements IZonaService {

	@Autowired
	private IZonaDao zonaDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ZonaResponseRest> search() {
		
		ZonaResponseRest response = new ZonaResponseRest();
		
		try {
			
			List<Zona> zona = (List<Zona>) zonaDao.findAll();
			response.getZonaResponse().setZona(zona);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<ZonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ZonaResponseRest>(response, HttpStatus.OK);
	}

}
