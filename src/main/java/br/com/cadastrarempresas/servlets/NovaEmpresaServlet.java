package br.com.cadastrarempresas.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.model.entitites.Empresa;
import br.com.cadastrarempresas.services.EmpresaService;

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
		String cnpjEmprsa = request.getParameter("cnpj");
		String dataEmpresa = request.getParameter("data");

		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		empresa.setCnpj(cnpjEmprsa);

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			empresa.setDataAbertura(sdf.parse(dataEmpresa));
		} catch (ParseException e) {
			throw new ServletException(e);

		}

		EmpresaService empServ = new EmpresaService();
		empServ.save(empresa);

		response.sendRedirect("ListaEmpresas");


	}
}
