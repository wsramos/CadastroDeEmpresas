package com.webapp.application;

import com.webapp.entities.Empresa;

public class App {
	
	public static void main(String[] args) {
		Empresa primeiraEmpresa = new Empresa();
		
		primeiraEmpresa.setId(1);	
		
		primeiraEmpresa.setName("Orbix");
		System.out.println(primeiraEmpresa);
		
		Empresa segundaEmpresa = new Empresa();
		segundaEmpresa.setId(2);
		
		System.out.println(primeiraEmpresa.equals(segundaEmpresa));
		
		
	}

}
