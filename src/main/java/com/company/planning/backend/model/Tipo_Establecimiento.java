package com.company.planning.backend.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_establecimientos")
public class Tipo_Establecimiento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5118129494233486898L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String codigo;
	
	private String nombre;
	private Boolean estado;
	
}
