package com.company.planning.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ZonaResponseRest> searchById(Long id) {
		
		ZonaResponseRest response = new ZonaResponseRest();
		List<Zona> list = new ArrayList<>();
		
		try {
			
			Optional<Zona> zona = zonaDao.findById(id);
			if (zona.isPresent()) {
				list.add(zona.get());
				response.getZonaResponse().setZona(list);
				response.setMetadata("Respuesta OK", "00", "Zona encontrada");
			} else {
				response.setMetadata("Respuesta no OK", "-1", "Error zona no encontrada");
				return new ResponseEntity<ZonaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al consultar zona");
			e.getStackTrace();
			return new ResponseEntity<ZonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ZonaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ZonaResponseRest> save(Zona zona) {
		ZonaResponseRest response = new ZonaResponseRest();
		List<Zona> list = new ArrayList<>();
		
		try {
			Zona zonaSaved = zonaDao.save(zona);
			if (zonaSaved != null) {
				list.add(zonaSaved);
				response.getZonaResponse().setZona(list);
				response.setMetadata("Respuesta OK", "00", "Zona guardada");
			} else {
				response.setMetadata("Respuesta no OK", "-1", "Zona no guardada");
				return new ResponseEntity<ZonaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al guardar la zona");
			e.getStackTrace();
			return new ResponseEntity<ZonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ZonaResponseRest>(response, HttpStatus.OK);
	}

	
	
}
