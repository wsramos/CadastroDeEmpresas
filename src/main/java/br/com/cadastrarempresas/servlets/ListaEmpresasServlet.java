package br.com.cadastrarempresas.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.model.dao.impl.EmpresaDaoJDBC;
import br.com.cadastrarempresas.model.entitites.Empresa;

/**
 * Servlet implementation class ListaEmpresasServlet
 */

@WebServlet("/ListaEmpresas")
public class ListaEmpresasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpresaDaoJDBC empDao = new EmpresaDaoJDBC();
		List<Empresa> lista = empDao.findAll();
		
		request.setAttribute("Empresas", lista);
		RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas.jsp");
		rd.forward(request, response);
		
	}

}
