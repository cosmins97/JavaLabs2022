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
  <h2>Size: <input type="number" name="inSize" value=${selectedSize} /></h2>
  <br>
  Captcha: How many edges does a ${captchaShape} have?
  <input type="number" name="captchaAnswer"/> <br/>
  <label>${captchaMessage}</label>
  <input type="submit" value="Submit" />
</form>
</body>
</html>
