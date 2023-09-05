package com.company.planning.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.planning.backend.dao.IProvinciaDao;
import com.company.planning.backend.dao.IZonaDao;
import com.company.planning.backend.model.Provincia;
import com.company.planning.backend.model.Zona;
import com.company.planning.backend.response.ProvinciaResponseRest;

@Service
public class ProvinciaServiceImpl implements IProvinciaService {
	
	private IZonaDao zonaDao;
	private IProvinciaDao provinciaDao;
	
	public ProvinciaServiceImpl(IProvinciaDao provinciaDao, IZonaDao zonaDao) {
		super();
		this.provinciaDao = provinciaDao;
		this.zonaDao = zonaDao;
	}


	@Override
	@Transactional
	public ResponseEntity<ProvinciaResponseRest> save(Provincia provincia, Long zonaid) {
		
		ProvinciaResponseRest response = new ProvinciaResponseRest();
		List<Provincia> list = new ArrayList<>();
		
		try {
			
			//buscamos la zona para para asignarle a la provincia
			Optional<Zona> zona = zonaDao.findById(zonaid);
			
			if ( zona.isPresent()) {
				provincia.setZona(zona.get());
			}else {
				response.setMetadata("Respuesta no OK", "-1", "Zona asociada a provincia no encontrada");
				return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//guardando la provincia
			Provincia provinciaSaved = provinciaDao.save(provincia);
			if (provinciaSaved != null) {
				list.add(provinciaSaved);
				response.getProvinciaResponse().setProvincia(list);
				response.setMetadata("Respuesta OK", "00", "Provincia guardada");
			} else {
				response.setMetadata("Respuesta no OK", "-1", "Provincia no guardada");
				return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al guardar provincia");
			e.getStackTrace();
			return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.OK);
	}




	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProvinciaResponseRest> searchById(Long id) {
		ProvinciaResponseRest response = new ProvinciaResponseRest();
		List<Provincia> list = new ArrayList<>();
		
		try {
			
			//buscamos el producto por id
			Optional<Provincia> provincia = provinciaDao.findById(id);
			
			if ( provincia.isPresent()) {
				list.add(provincia.get());
				response.getProvinciaResponse().setProvincia(list);
				response.setMetadata("Respuesta OK", "00", "Provincia encontrada");
			}else {
				response.setMetadata("Respuesta no OK", "-1", "Provincia no encontrada");
				return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta no OK", "-1", "Error al buscar provincia");
			return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProvinciaResponseRest> searchByName(String nombre) {
		ProvinciaResponseRest response = new ProvinciaResponseRest();
		List<Provincia> list = new ArrayList<>();
		List<Provincia> listAux = new ArrayList<>();
		
		try {
			
			//buscamos el producto por name
			listAux = provinciaDao.findByNameContainingIgnoreCase(nombre);
			
			if ( listAux.size() > 0) {
				listAux.stream().forEach( (p) -> {
					list.add(p);
				});
				response.getProvinciaResponse().setProvincia(list);
				response.setMetadata("Respuesta OK", "00", "Provincia encontrada");
				
			}else {
				response.setMetadata("Respuesta no OK", "-1", "Provincia no encontrada");
				return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta no OK", "-1", "Error al buscar provincia");
			return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional
	public ResponseEntity<ProvinciaResponseRest> deleteById(Long id) {
		ProvinciaResponseRest response = new ProvinciaResponseRest();
		
		try {
			
			//Borramos producto por id
			provinciaDao.deleteById(id);
			response.setMetadata("Respuesta OK", "00", "Provincia eliminada");
		}catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta no OK", "-1", "Error al eliminar provincia");
			return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProvinciaResponseRest>(response, HttpStatus.OK);
	}

	

}
