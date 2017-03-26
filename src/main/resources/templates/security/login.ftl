<html>
	<body>
	
	<form action="" method="POST">
        <div align="center">
            <table>
                <tr>
                    <td>User Name</td>
                    <td><input type="text" name="username" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                    			<input type="hidden" 
                    				name="${_csrf.parameterName}"
									value="${_csrf.token}"/	>
								<input type="submit" value="submit" /></td>
                </tr>
            </table>
        </div>
       </form>
		</body>
</html>
