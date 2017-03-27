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
			<div class="col-12">
				<div class="row" id="connect">
					<div class="col-1">
					</div>
					<div class="col-6" id="NikoTitle">
					   <img src='./images/bandeau.png' alt='' id="logo">
					</div>
					<div class="col-4" id="VerticaleEquipe">
						<p id="verticaleName">Verticale: ${verticale}</p>
						<p id="teamName">Equipe: ${equipe}</p>
					</div>
					<div class="col-1">
					</div>
				</div>
				<div class="row">
					<div class="col-1"></div>
					<div class="col-10" id="formulaire1">
						<form  ENCTYPE="multipart/form-data" method="post" action="inputDateSave">
							<div class="row divDateDuJour">
								<div class="col-1"></div>
								<div class="col-2">
									<button class="button" id="jourPreced" onclick="setJourPreced(5)"></button>
								</div>
								<div class="col-6">
									<span id = "DateDuJour" >${newDayDate?string("dd MMMM yyyy")}</span>
								</div>
								<div class="col-2">
									<button class="button" id="joursuivant" onclick="setJourSuiv()"></button>
								</div>
								<div class="col-1"></div>
								<input type="hidden" id="newDayDate" name="newDayDate" value=${newDayDate?string("yyyy/MM/dd HH:mm:ss")}>
								<input type="hidden" id="newDayDateStr" name="newDayDateStr" value=${newDayDate?string("yyyy-MM-dd-HH-mm-ss")}>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						</form>
					</div>
					<div class="col-1"></div>
				</div>
				<div class="row">
					<div class="col-1"></div>
					<div class="col-10">
						<form action="" method="post" id="formulaire2">
							<div class="dayQuestion">
								<p> Bonjour ${prenomUser} ${nomUser}, <p>
								<p>Comment s'est passée votre journée ?</p>
							</div>
							<div class="row "nikoNikoDuJour">
								<div class="col-3" id="nikoImg">
									<canvas id="canvas" width="100" height="100"></canvas>
								</div>
								<div class="col-9 nikoComment" >
									<textarea form="formulaire2" name="comment" id="nikoComment" maxlength="160" placeholder="Tapez vos commentaires ici">${nikoComment}</textarea>
								</div>
							</div>
							<div >
								<input type="submit" class="button" id="valider" src="./images/validation.png" >
								<input type="hidden" id="satisfaction" name="satisfaction" value="0">
								<input type="hidden" id="isanonymous" name="is_anonymous" value=${isanonymous?c}>
								<input type="hidden" id="nikoId" name="nikoId" value=${nikoId}>
								<input type="hidden" id="log_date" name="Log_date" value=${log_date?string("yyyy/MM/dd HH:mm:ss")}>
								<input type="hidden" id="newDayDate" name="newDayDate" value=${newDayDate?string("yyyy/MM/dd HH:mm:ss")}>
								<!--#include "../includable/security/securityToken.ft"-->
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						 </form>
					</div>
					<div class="col-1"></div>
				</div>
				<div class="row">
					<div class="col-1"></div>
					<div class="col-11">
						<form action="quit" method="post" id="deconnexion">
							<div id="quitMenue">
								<input type="submit" class="image" id="deconnexion">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>
						</form>
					</div>
				</div>
			<!--/div>
			<div class="col-2 rightArea">
				<div id="inputMenue">
					<a href="input.html">
					<img src='./images/vote.png' alt='' class="imgMenue"></a>
				</div>
				<div id="consultMenue">
					<a href="calendrier.html">
					<img src='./images/resultat.png' alt='' class="imgMenue"></a>
				</div>

				<form action="" method="post" id="deconnexion">
					<div id="quitMenue">
						<input type="submit" class="image" id="deconnexion">
					</div>
				</form>
			</div-->
		</div>
    </div>


    <!-- Lancement des scripts -->
    <!--#######################-->

    <script type="text/javascript" src="js/create_niko.js">
    </script>
    <script>
       $(document).ready(function() {
           callCreateNiko("nikoImg",${nikoSatisfaction});
       })
    </script>
  </body>
</html>
