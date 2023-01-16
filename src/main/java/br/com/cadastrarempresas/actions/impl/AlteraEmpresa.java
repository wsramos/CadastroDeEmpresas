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

public class AlteraEmpresa implements Action {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmpresaService empServ = new EmpresaService();
		Empresa emp = new Empresa();

		emp.setId(Integer.parseInt(request.getParameter("id")));
		emp.setNome(request.getParameter("nome"));
		
		if(request.getParameter("cnpj").contains(".") || request.getParameter("cnpj").contains("/") || request.getParameter("cnpj").contains("-")) {
			emp.setCnpj(request.getParameter("cnpj").replaceAll("-","").replaceAll("\\p{Punct}", "").replaceAll("/",""));
		} else {
			  emp.setCnpj(request.getParameter("cnpj"));
		}

		try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            emp.setDataAbertura(sdf.parse(request.getParameter("data")));
        } catch (ParseException e) {
            throw new ServletException(e);

        }
		empServ.updateAll(emp);
		return "redirect:in?action=ListaEmpresas";
	}

}
