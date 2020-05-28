package com.uca.capas.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="contribuyente")
public class Contribuyente {
	
	@Id
	@Column(name="c_contribuyente")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(generator="contribuyente_c_contribuyente_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "contribuyente_c_contribuyente_seq", sequenceName = "public.contribuyente_c_contribuyente_seq", allocationSize = 1)
	private Integer ccontribuyente;
	
	@Size(message="El nombre no debe tener mas de 30 caracteres", max = 30)
	@NotEmpty(message="Este campo no puede estar vacío") 
	@Column(name="s_nombre")
	private String snombre;
	
	@Size(message="El apellido no debe tener mas de 30 caracteres", max = 30)
	@NotEmpty(message="Este campo no puede estar vacío") 
	@Column(name="s_apellido")
	private String sapellido;
	
	@Size(message="El NIT debe tener 14 digitos", min=14, max=14)
	@Column(name="s_nit")
	private String snit;
	
	@Column(name="f_fecha_ingreso")
	private Date sfechaingreso;
	
	@Transient
	private Integer cimportancia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_importancia")
	private Importancia importancia;
	
	
	public Importancia getImportancia() {
		return importancia;
	}
	public void setImportancia(Importancia importancia) {
		this.importancia = importancia;
	}
	public Integer getCimportancia() {
		return cimportancia;
	}
	public void setCimportancia(Integer cimportancia) {
		this.cimportancia = cimportancia;
	}
	public Integer getCcontribuyente() {
		return ccontribuyente;
	}
	public void setCcontribuyente(Integer ccontribuyente) {
		this.ccontribuyente = ccontribuyente;
	}
	public String getSnombre() {
		return snombre;
	}
	public void setSnombre(String snombre) {
		this.snombre = snombre;
	}
	public String getSapellido() {
		return sapellido;
	}
	public void setSapellido(String sapellido) {
		this.sapellido = sapellido;
	}
	public String getSnit() {
		return snit;
	}
	public void setSnit(String snit) {
		this.snit = snit;
	}
	public Date getSfechaingreso() {
		return sfechaingreso;
	}
	public void setSfechaingreso(Date sfechaingreso) {
		this.sfechaingreso = sfechaingreso;
	}
	
	public Contribuyente() {
		super();
	}
	
	public String getSfechaDelegate(){
		if(this.sfechaingreso == null){
			return "";
		}
		else{
			return new SimpleDateFormat("dd/MM/yyyy").format(this.sfechaingreso.getTime());
		}
	}

}
