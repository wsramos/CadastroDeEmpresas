package br.com.cadastrarempresas.actions.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.actions.Action;

/**
 * Servlet implementation class NovaEmpresaServlet
 */

public class NovaEmpresa implements Action {
	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "forward:cadastrarNovaEmpresa.jsp";

	}
}
