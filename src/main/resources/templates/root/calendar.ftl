<!DOCTYPE html>
<html lang="en">
  <head>
	<meta charset="utf-8">
	<title>Exemples JavaScript</title>
	<meta name="viewport" content="initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<link href="css/nikoniko_regis_denis.css" rel="stylesheet" >

	<#include "../includable/bootstrap.ftl">
	<#include "../includable/jquery.ftl">
  </head>

  <body>
	<canvas id="canvas" width="25" height="25"></canvas>
	<div class="container" id="container">
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10">
				<div class="row NikoInputTitle">
					<div class="col-xs-8 ">
					   <img src='./images/bandeau.png' alt='' id="logo">
					</div>
					<div class="col-xs-2 " id="VerticaleEquipeTitre">
						<p id="verticaleTitle">Verticale:</p>
						<p id="teamName">Equipe:</p>
					</div>
					<div class="col-xs-2 " id="VerticaleEquipeName">
						<p id="verticaleTitle">${verticale}</p>
						<p id="teamTitle">${equipe}</p>
					</div>
				</div>
			</div>
			<div class="col-xs-1"></div>
		</div>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10">
				<div id="calendar"></div>
			</div>
			<div class="col-xs-1"></div>
		</div>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10"  id="NikoFooter"></div>
			<div class="col-xs-1"></div>
		</div>
		<div class="row" id="menu">
			<div class="col-xs-8"></div>
			<div class="col-xs-1">
				        <form action="result/1/0/by_team_by_week" method="get" id="by_team_by_week" >
				         	<div id="bargraph">
					        	<input type="submit" class="menu_button" id="bargraph_week" value="">
					        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        	</div>
				         </form>
				    </div>
			<div class="col-xs-1">
				<form action="inputNiko2" method="post" id="inputNiko" >
					<div id="voteMenue">
						<input type="submit" class="menu_button" id="inputNiko" value="">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</div>
				</form>
			</div>
			<div class="col-xs-1">
				<form action="quit" method="post" id="deconnexion">
					<div id="quitMenue">
						<input type="submit" class="menu_button" id="deconnexion" value="">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</div>
				</form>
			</div>
			<div class="col-xs-1"></div>
		</div>
	</div>


	<!-- Lancement des scripts -->
	<!--#######################-->
	<script type="text/javascript" src="js/create_niko.js"></script>
	<script type="text/javascript" src="js/function.js"> </script>
	<script>
	   $(document).ready(function() {
			createWeek(new Date());
	   })
	</script>
  </body>
</html>
