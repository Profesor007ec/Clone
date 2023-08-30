package com.company.planning.backend.model;

import java.io.Serializable;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "establecimientos")
public class Establecimiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7100794093106195437L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String unicodigo;
	
	private String nombre;
	private String tipo_establecimiento;
	private String nivel_establecimiento;
	private String institucion;
	
	@OneToOne
	@JsonIgnoreProperties({"hibernateLazyInitializater","handler"})
	private Parroquia parroquia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializater","handler"})
	private Entidad entidad;

}
