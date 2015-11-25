<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

	function cerrar(){	
	window.returnValue ="${defineIva}";	
	window.close();
	}

</script>
</head>
<body>
<input type="text" name="procentajeIva1" id="procentajeIva1" value="${porcentajeIva}"/>
	<script>
		cerrar();
	</script>
</body>
</html>