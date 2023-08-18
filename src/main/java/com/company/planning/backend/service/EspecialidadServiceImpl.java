package com.company.planning.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<EspecialidadResponseRest> searchById(Long id) {
		
		EspecialidadResponseRest response = new EspecialidadResponseRest();
		List<Especialidad> list = new ArrayList<>();
		
		try {
			
			Optional<Especialidad> especialidad = especialidadDao.findById(id);
			
			if (especialidad.isPresent()) {
				list.add(especialidad.get());
				response.getEspecialidadResponse().setEspecialidad(list);
				response.setMetadata("Respuesta OK", "00", "Especialidad encontrada");
			}else {
				response.setMetadata("Respuesta no OK", "-1", "Error especialidad no encontrada");
				return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al consultar la especialidad");
			e.getStackTrace();
			return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EspecialidadResponseRest> save(Especialidad especialidad) {
		
		EspecialidadResponseRest response = new EspecialidadResponseRest();
		List<Especialidad> list = new ArrayList<>();
		
		try {
			
			Especialidad especialidadSaved = especialidadDao.save(especialidad);
			
			if (especialidadSaved != null) {
				list.add(especialidadSaved);
				response.getEspecialidadResponse().setEspecialidad(list);
				response.setMetadata("Respuesta OK", "00", "Especialidad guardada");
			}else {
				response.setMetadata("Respuesta no OK", "-1", "Especialidad no guardada");
				return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al grabar especialidad");
			e.getStackTrace();
			return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EspecialidadResponseRest> update(Especialidad especialidad, Long id) {
		
		EspecialidadResponseRest response = new EspecialidadResponseRest();
		List<Especialidad> list = new ArrayList<>();
		
		try {
			
			Optional<Especialidad> especialidadSearch = especialidadDao.findById(id);
			if (especialidadSearch.isPresent()) {
				//Se procederá  actualizar el registro
				especialidadSearch.get().setCodigo(especialidad.getCodigo());
				especialidadSearch.get().setNombre(especialidad.getNombre());
				especialidadSearch.get().setEstado(especialidad.getEstado());
				
				Especialidad especialidadToUpdate = especialidadDao.save(especialidadSearch.get());
				if (especialidadToUpdate != null) {
					list.add(especialidadToUpdate);
					response.getEspecialidadResponse().setEspecialidad(list);
					response.setMetadata("Respuesta OK", "00", "Especialidad actualizada");
				}else {
					response.setMetadata("Respuesta no OK", "-1", "Especialidad no actualizada");
					return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				response.setMetadata("Respuesta no OK", "-1", "Especialidad no encontrada");
				return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al actualizar especialidad");
			e.getStackTrace();
			return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<EspecialidadResponseRest> deleteById(Long id) {
		
		EspecialidadResponseRest response = new EspecialidadResponseRest();
		
		try {
			
			especialidadDao.deleteById(id);
			response.setMetadata("Respuesta OK", "00", "Regitro eliminado");
			
		}catch (Exception e) {
			response.setMetadata("Respuesta no OK", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EspecialidadResponseRest>(response, HttpStatus.OK);
	}

}
