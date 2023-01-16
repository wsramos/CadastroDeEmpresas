package br.com.cadastrarempresas.actions.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.actions.Action;
import br.com.cadastrarempresas.controllers.services.EmpresaService;

public class RemoveEmpresa implements Action {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmpresaService empServ = new EmpresaService();
		empServ.remove(Integer.parseInt(request.getParameter("id")));
		return "redirect:in?action=ListaEmpresas";
	}

}
