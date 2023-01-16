<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/in" var="linkServletEntradaAlteraEmpresa" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<c:import url="Logout-parcial.jsp"/>
		<form action="${linkServletEntradaAlteraEmpresa}" method="post">
			Nome: <input type="text" name="nome" value="${empresa.nome }"/>
			CNPJ: <input type="text" name="cnpj" value="${empresa.cnpj }"/>
			Data de Abertura: <input type="text" name="data" value="<fmt:formatDate value="${empresa.dataAbertura }" pattern="dd/MM/yyyy"/>"/>
			<input type="hidden" name="id" value="${empresa.id }"/>
			<input type="hidden" name="action" value="AlteraEmpresa"/>
			<input type="submit" />
		</form>
	</body>
</html>