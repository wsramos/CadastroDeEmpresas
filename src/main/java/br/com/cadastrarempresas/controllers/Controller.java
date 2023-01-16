package br.com.cadastrarempresas.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cadastrarempresas.actions.Action;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/in")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "";
		try {
			Action actionImp = (Action) Class.forName("br.com.cadastrarempresas.actions.impl." + request.getParameter("action")).newInstance();
			view = actionImp.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] address = view.split(":");
		if("forward".equals(address[0])) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/" + address[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(address[1]);
		}
		
	}

}
