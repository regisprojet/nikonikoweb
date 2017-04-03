<div class="row">
    <div class="col-xs-10"></div>
	<div class="col-xs-1">
		<form action="/admin2/index" method="post" id="homeButton" >
	 		<div id="home">
				<input type="submit" class="menu_button" id="homeButton" value="">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</form>
	</div>
	<div class="col-xs-1">
		<form action="/logout" method="post" id="deconnexion">
			<div id="quitMenue">
				<input type="submit" class="menu_button" id="deconnexion" value="">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 		</div>
		</form>
	</div>
</div>
