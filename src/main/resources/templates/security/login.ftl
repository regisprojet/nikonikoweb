<html>
	<body>
		<form  method="post" action="">
			<input type="text" name="username">
			<input type="password" name="password">
			<input type="hidden"
				name="${_csrf.parameterName}"
				value="${_csrf.token}"/>
			<input type="submit" value="validate"/>
		</form>
	</body>
</html>
