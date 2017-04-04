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
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
  </head>

  <body>
	<canvas id="canvas" width="25" height="25"></canvas>
	<div class="container" id="container">
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10">
				<div class="row NikoInputTitle">
					<div class="col-xs-7 ">
					   <img src='./images/bandeau.png' alt='' id="logo">
					</div>
					<div class="col-xs-2 " id="VerticaleEquipeTitre">
						<p id="verticaleTitle">Verticale:</p>
						<p id="teamTitle">Equipe:</p>
					</div>
					<div class="col-xs-3 " id="VerticaleEquipeName">
						<p id="verticaleName">${verticale}</p>

						<form id="teamSelect" ENCTYPE="multipart/form-data" method="post" action="calendarTeamSelect">
							<select onChange="this.form.submit()" name="team" size="1">
								<#list equipes as equipe>
									<#if "${equipe.name}"  ==  "${equipeSelect}">
										<OPTION selected>${equipe.name}
									<#else>
										<OPTION>${equipe.name}
									</#if>
								</#list>
							</select>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
					</div>
				</div>
			</div>
			<div class="col-xs-1"></div>
		</div>

		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10" id="formulaire1">
				<div class="row divDateDuJour">
					<div class="col-xs-1"></div>
					<div class="col-xs-2">
					<form  ENCTYPE="multipart/form-data" method="post" action="calendarDatepreded">
						<button class="button" id="Precedant"></button>
						<input type="hidden" id="newDayDate" name="newDayDate" value=${newDayDate?string("yyyy/MM/dd HH:mm:ss")}>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
					</div>
					<div class="col-xs-6">
						<span id = "DateDuJour" >${newDayDate?string("MMMM yyyy")}</span>
					</div>
					<div class="col-xs-2">
					<form  ENCTYPE="multipart/form-data" method="post" action="calendarDatesuiv">
						<button class="button" id="suivant"></button>
						<input type="hidden" id="newDayDate" name="newDayDate" value=${newDayDate?string("yyyy/MM/dd HH:mm:ss")}>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
					</div>
					<div class="col-xs-1"></div>
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
					        	<input type="submit" class="menu_button" id="bargraphWeek" value="">
					        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        	</div>
				         </form>
				    </div>
			<div class="col-xs-1">
				<form action="calendarInputNiko" method="post" id="inputNiko" >
					<div id="voteMenue">
						<input type="submit" class="menu_button" id="inputNiko" value="">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</div>
				</form>
			</div>
			<div class="col-xs-1">
				<form action="calendarLogout" method="post" id="deconnexion">
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
	$(function () {
		var nikonikos  = new Array();
		var i =0;
		<#list nikos as niko>
			nikonikos[i] = new Array();
        	nikonikos[i][0] = "${niko.satisfaction}";
        	nikonikos[i][1] = "${niko.log_date}";
        	nikonikos[i][2] = "${niko.comment}";
        	nikonikos[i][3] = "${niko.user.lastname}";
        	nikonikos[i][4] = "${niko.user.firstname}";
        	nikonikos[i][5] = "${niko.user.login}";
        	i++;
        </#list>

	    $(document).ready(function() {
	    	  $('[data-toggle="popover"]').popover()
		})
			createWeek("${newDayDateStr}", nikonikos);
	    })
	</script>
  </body>
</html>
