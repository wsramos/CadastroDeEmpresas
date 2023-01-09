package com.webapp.entities;

import java.util.Date;
import java.util.Objects;

public class Empresa {
	private Integer id;
	private String name;
	private String cnpj;
	private Date dataDeAbertura;
	
	public Empresa() {
		
	}
	
	public Empresa(String name, String cnpj, Date dataDeAbertura) {
		super();
		this.name = name;
		this.cnpj = cnpj;
		this.dataDeAbertura = dataDeAbertura;
	}
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Date getDataDeAbertura() {
		return dataDeAbertura;
	}
	public void setDataDeAbertura(Date dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", name=" + name + ", cnpj=" + cnpj + ", dataDeAbertura=" + dataDeAbertura + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(id, other.id);
	}

	
}
