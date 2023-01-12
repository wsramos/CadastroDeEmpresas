package br.com.cadastrarempresas.model.entitites;

import java.util.Date;
import java.util.Objects;

public class Empresa {
	private Integer id;
	private String nome;
	private String cnpj;
	private Date dataAbertura;
	
	public Empresa() {
		
	}
	
	public Empresa(String name, String cnpj, Date dataDeAbertura) {
		super();
		this.nome = name;
		this.cnpj = cnpj;
		this.dataAbertura = dataDeAbertura;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", name=" + nome + ", cnpj=" + cnpj + ", dataAbertura=" + dataAbertura + "]";
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
