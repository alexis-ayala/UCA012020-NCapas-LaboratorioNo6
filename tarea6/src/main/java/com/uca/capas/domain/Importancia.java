package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="importancia")
public class Importancia {
	@Id
	@Column(name="c_importancia")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cimportancia;
	
	@Column(name="s_importancia")
	private String simportancia;
	
	public Integer getCimportancia() {
		return cimportancia;
	}
	public void setCimportancia(Integer cimportancia) {
		this.cimportancia = cimportancia;
	}
	public String getSimportancia() {
		return simportancia;
	}
	public void setSimportancia(String simportancia) {
		this.simportancia = simportancia;
	}
	
	public Importancia() {
		super();
	}
}
