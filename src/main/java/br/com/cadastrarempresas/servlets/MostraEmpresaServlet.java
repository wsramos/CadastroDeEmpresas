package br.com.cadastrarempresas.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.model.dao.impl.EmpresaDaoJDBC;
import br.com.cadastrarempresas.model.entitites.Empresa;

/**
 * Servlet implementation class MostraEmpresaServlet
 */
@WebServlet("/MostraEmpresa")
public class MostraEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpresaDaoJDBC empDao = new EmpresaDaoJDBC();
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Empresa empresa = empDao.findById(id);
		
		System.out.println("Editando a empresa " + empresa.getNome());
		
		RequestDispatcher rd = request.getRequestDispatcher("/formAlteraEmpresa.jsp");
		request.setAttribute("empresa", empresa);
		rd.forward(request, response);
	}

}
