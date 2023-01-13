package br.com.cadastrarempresas.actions.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.actions.Action;
import br.com.cadastrarempresas.model.dao.impl.EmpresaDaoJDBC;
import br.com.cadastrarempresas.model.entitites.Empresa;

/**
 * Servlet implementation class MostraEmpresaServlet
 */
public class MostraEmpresa implements Action {
	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Empresa empresa = new EmpresaDaoJDBC().findById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("empresa", empresa);
		
		System.out.println("Editando a empresa " + empresa.getNome());
		
		return "forward:formAlteraEmpresa.jsp";
	}

}
