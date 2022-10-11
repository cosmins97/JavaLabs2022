<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Lab 2 - Input</title>
</head>
<body>
<h2>Input</h2>
<form name="inputForm" method="post" action="input">
  <h2>Word: <input type="text" name="inWord"/></h2>
  <br/>
  <h2>Size: <input type="number" name="inSize" /></h2>
  <br>
  <input type="submit" value="Submit" />
</form>
</body>
</html>
