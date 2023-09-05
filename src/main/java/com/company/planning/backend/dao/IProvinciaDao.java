package com.company.planning.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.planning.backend.model.Provincia;

public interface IProvinciaDao extends CrudRepository<Provincia, Long> {
	
	@Query("select p from Provincia p where p.nombre like %?1%")
	List<Provincia> findByNameLike(String nombre);
	
	@Query("select p from Provincia p where p.nombre like %?1%")
	List<Provincia> findByNameContainingIgnoreCase(String nombre);

}
