package br.com.cadastrarempresas.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.model.dao.impl.EmpresaDaoJDBC;
import br.com.cadastrarempresas.model.entitites.Empresa;

/**
 * Servlet implementation class NovaEmpresaServlet
 */

@WebServlet("/NovaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Cadastrando nova empresa");
		String nomeEmpresa = request.getParameter("nome");
		String dataEmpresa = request.getParameter("data");

		Empresa empresa = new Empresa();
		empresa.setName(nomeEmpresa);

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			empresa.setDataDeAbertura(sdf.parse(dataEmpresa));
		} catch (ParseException e) {
			throw new ServletException(e);

		}

		EmpresaDaoJDBC empDao = new EmpresaDaoJDBC();
		empDao.insert(empresa);

		response.sendRedirect("listaEmpresas");


	}
}
