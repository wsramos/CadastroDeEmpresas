package br.com.cadastrarempresas.model.dao;

import br.com.cadastrarempresas.model.dao.dbaccess.DB;
import br.com.cadastrarempresas.model.dao.impl.EmpresaDaoJDBC;
import br.com.cadastrarempresas.model.dao.interfaces.EmpresaDao;

public class DaoFactory {	
	
	public static EmpresaDao createEmpresaDao() {
		return new EmpresaDaoJDBC(DB.getConnection());
	}
	
}