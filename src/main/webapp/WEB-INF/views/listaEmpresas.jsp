<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, br.com.cadastrarempresas.model.entitites.Empresa"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Java Standard Taglib</title>
	</head>
	
	<body>
	
		<c:if test="${not empty Empresa}">
			Empresa ${Empresa} Cadastrada com sucesso!
		</c:if>
		<br/>
		<br/>
		Lista Empresas:
		<ul>
			<c:forEach items="${Empresas}" var="empresa">
				<li>
					${empresa.nome} -
					<fmt:formatDate value="${empresa.dataAbertura }" pattern="dd/MM/yyyy"/>
					${empresa.cnpj} -
					<a href="/webapp/in?id=${empresa.id}&action=MostraEmpresa">Editar</a> 
					<a href="/webapp/in?id=${empresa.id}&action=RemoveEmpresa" onclick="return confirm('Are you sure you want to continue')">Remover</a>
				</li>
			</c:forEach>
		</ul>
		<script type="text/javascript">
</script>
		
	</body>
</html>