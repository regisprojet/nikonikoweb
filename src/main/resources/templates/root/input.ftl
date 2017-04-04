<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "../includable/bootstrap.ftl">
    <#include "../includable/jquery.ftl">

    <link href="css/nikoniko_regis_denis.css" rel="stylesheet" >
  </head>

  <body>
    <div class="container" id="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="row">
					<div class="col-xs-1"></div>
					<div class="col-xs-5 NikoInputTitle">
					   <img src='./images/bandeau.png' alt='' id="logo">
					</div>
					<div class="col-xs-2 NikoInputTitle" id="VerticaleEquipeTitre">
						<p id="verticaleTitle">Verticale:</p>
						<p id="teamName">Equipe:</p>
					</div>
					<div class="col-xs-3 NikoInputTitle" id="VerticaleEquipeName">
						<p id="verticaleTitle">${verticale}</p>
						<form id="teamSelect" ENCTYPE="multipart/form-data" method="post" action="inputTeamSelect">
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
					<div class="col-xs-1"></div>
				</div>
				<div class="row">
					<div class="col-xs-1"></div>
					<div class="col-xs-10" id="formulaire1">
						<form  ENCTYPE="multipart/form-data" method="post" action="inputDateSave">
							<div class="row divDateDuJour">
								<div class="col-xs-1"></div>
								<div class="col-xs-2">
									<button class="button" id="Precedant" onclick="setJourPreced(3)"></button>
								</div>
								<div class="col-xs-6">
									<span id = "DateDuJour" >${newDayDate?string("dd MMMM yyyy")}</span>
								</div>
								<div class="col-xs-2">
									<button class="button" id="suivant" onclick="setJourSuiv()"></button>
								</div>
								<div class="col-xs-1"></div>
								<input type="hidden" id="newDayDate" name="newDayDate" value=${newDayDate?string("yyyy/MM/dd HH:mm:ss")}>
								<input type="hidden" id="newDayDateStr" name="newDayDateStr" value=${newDayDate?string("yyyy-MM-dd-HH-mm-ss")}>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						</form>
					</div>
					<div class="col-xs-1"></div>
				</div>
				<div class="row">
					<div class="col-xs-1"></div>
					<div class="col-xs-10">
						<form action="" method="post" id="formulaire2">
							<div class="dayQuestion">
								<p> Bonjour ${prenomUser} ${nomUser}, <p>
								<p>Comment s'est pass&eacute; votre journ&eacute;e ?</p>
							</div>
							<div class="row "nikoNikoDuJour">
								<div class="col-xs-3" id="nikoImg">
									<canvas id="canvas" width="100" height="100"></canvas>
								</div>
								<div class="col-xs-9 nikoComment" >
									<textarea form="formulaire2" name="comment" id="nikoComment" maxlength="160" placeholder="Tapez vos commentaires ici">${nikoComment}</textarea>
								</div>
							</div>
							<div >
								<input type="submit" class="button" id="valider" src="./images/validation.png" >
								<input type="hidden" id="satisfaction" name="satisfaction" value="0">
								<input type="hidden" id="isanonymous" name="is_anonymous" value=${isanonymous?c}>
								<input type="hidden" id="nikoId" name="nikoId" value=${nikoId?c}>
								<input type="hidden" id="log_date" name="Log_date" value=${log_date?string("yyyy/MM/dd HH:mm:ss")}>
								<input type="hidden" id="newDayDate" name="newDayDate" value=${newDayDate?string("yyyy/MM/dd HH:mm:ss")}>
								<!--#include "../includable/security/securityToken.ft"-->
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						 </form>
					</div>
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
						<form action="calendar2" method="post" id="restitution" >
							<div id="restMenue">
								<input type="submit" class="menu_button" id="restitution" value="">
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
		</div>
    </div>


    <!-- Lancement des scripts -->
    <!--#######################-->

    <script type="text/javascript" src="js/create_niko.js">
    </script>
    <script>
		$(document).ready(function() {
			callCreateNiko("nikoImg","yes",${nikoSatisfaction});
       })
    </script>
  </body>
</html>
