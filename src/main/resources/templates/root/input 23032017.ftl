<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "../includable/bootstrap.ftl">
    <#include "../includable/jquery.ftl">
    <link href="css/nikoniko_regis_denis.css" rel="stylesheet" >
    <script type="text/javascript" src="js/create_niko.js"></script>

  </head>

  <body>
    <div class="container" id="container">
		<div class="row">
			<div class="col-10">
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
						<!--form  ENCTYPE="multipart/form-data" method="post" action=""-->
							<div class="row divDateDuJour">
								<div class="col-1"></div>
								<div class="col-2">
									<button class="button" id="jourPreced" onclick="setJourPreced(25)"></button>
								</div>
								<div class="col-6">
									<span id = "DateDuJour" ></span>
								</div>
								<div class="col-2">
									<button class="button" id="joursuivant" onclick="setJourSuiv()"></button>
								</div>
								<div class="col-1"></div>
								<input type="hidden" id="newDayDate" name="newDayDate" value=${newDayDate}>
							</div>
						<!--/form-->
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
							<div class="row "nikoNikoDuJour"">
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
								<input type="hidden" id="isanonymous" name="isanonymous" value=0>

								<input type="hidden" id="nikoId" name="nikoId" value=${nikoId}>
							</div>
						 </form>
					</div>
					<div class="col-1"></div>
				</div>
			</div>
			<div class="col-2 rightArea">
				<div id="inputMenue">
					<a href="input.html">
					<img src='./images/vote.png' alt='' class="imgMenue"></a>
				</div>
				<div id="consultMenue">
					<a href="calendrier.html">
					<img src='./images/resultat.png' alt='' class="imgMenue"></a>
				</div>
				<div id="quitMenue">
					<a href="index.html">
					<img src='./images/deconnexion.png' alt='' class="imgMenue"></a>
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
           callCreateNiko("nikoImg",${nikoSatisfaction});
       })
    </script>
  </body>
</html>