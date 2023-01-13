<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/in" var="linkServletNovaEmpresa" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="${linkServletNovaEmpresa}" method="post">
			Nome: 				<input type="text" name="nome"/>
			CNPJ: 				<input type="text" name="cnpj">
			Data de Abertura: 	<input type="text" name="data">
								<input type="hidden" name="action" value="CadastraEmpresa"/>
			<input type="submit" />
		</form>
	</body>
</html>