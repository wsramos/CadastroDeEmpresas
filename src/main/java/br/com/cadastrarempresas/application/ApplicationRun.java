package br.com.cadastrarempresas.application;

import br.com.cadastrarempresas.model.entitites.Empresa;

public class ApplicationRun {
	
	public static void main(String[] args) {
		Empresa primeiraEmpresa = new Empresa();
		
		
		primeiraEmpresa.setNome("Orbix");
		System.out.println(primeiraEmpresa);
		
		Empresa segundaEmpresa = new Empresa();
		
		System.out.println(primeiraEmpresa.equals(segundaEmpresa));
		
		
	}

}