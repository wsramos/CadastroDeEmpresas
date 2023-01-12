package br.com.cadastrarempresas.model.dao.interfaces;

import java.util.List;

import br.com.cadastrarempresas.model.entitites.Empresa;

public interface EmpresaDao {
	void insert(Empresa obj);
	void updateAtributteById(Integer id, String atributo, String valorAtributo);
	void updateAllById(Empresa obj);
	void deleteById(Integer id);
	Empresa findById(Integer id);
	List<Empresa> findAll();
}
