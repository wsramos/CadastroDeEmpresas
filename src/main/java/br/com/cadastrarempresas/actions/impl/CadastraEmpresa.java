package br.com.cadastrarempresas.actions.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.actions.Action;
import br.com.cadastrarempresas.controllers.services.EmpresaService;
import br.com.cadastrarempresas.model.entitites.Empresa;

/**
 * Servlet implementation class NovaEmpresaServlet
 */

public class CadastraEmpresa implements Action {
	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Cadastrando nova empresa");

		Empresa empresa = new Empresa();
		empresa.setNome(request.getParameter("nome"));
		empresa.setCnpj(request.getParameter("cnpj"));

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			empresa.setDataAbertura(sdf.parse(request.getParameter("data")));
		} catch (ParseException e) {
			throw new ServletException(e);

		}

		EmpresaService empServ = new EmpresaService();
		empServ.save(empresa);
		return "redirect:in?action=ListaEmpresas";

	}
}
