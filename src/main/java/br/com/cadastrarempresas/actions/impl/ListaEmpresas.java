package br.com.cadastrarempresas.actions.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.actions.Action;
import br.com.cadastrarempresas.controllers.services.EmpresaService;
import br.com.cadastrarempresas.model.entitites.Empresa;


public class ListaEmpresas implements Action {
    
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpresaService eservice = new EmpresaService();
		List<Empresa> lista = eservice.findAll();
		
		request.setAttribute("Empresas", lista);
		
		return "forward:listaEmpresas.jsp";
		
	}

}
