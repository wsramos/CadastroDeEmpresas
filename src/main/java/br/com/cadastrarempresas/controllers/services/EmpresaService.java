package br.com.cadastrarempresas.controllers.services;

import java.util.List;

import br.com.cadastrarempresas.model.dao.DaoFactory;
import br.com.cadastrarempresas.model.dao.interfaces.EmpresaDao;
import br.com.cadastrarempresas.model.entitites.Empresa;

public class EmpresaService {

private EmpresaDao dao = DaoFactory.createEmpresaDao();
	
	public List<Empresa> findAll(){
		return dao.findAll();
	}
	
	public void save(Empresa obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
	}
	
	public void updateAll(Empresa obj) {
		dao.updateAllById(obj);
	}
	
	public void updateByAttr(Integer id, String attribute, String value) {
		dao.updateAtributteById(id, attribute, attribute);
	}
	
	public void remove(Empresa obj) {
		dao.deleteById(obj.getId());
	}
}
