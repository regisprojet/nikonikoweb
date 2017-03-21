<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Exemples JavaScript</title>
    <meta name="viewport" content="initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="css/nikoniko_regis_denis.css" rel="stylesheet" >

    <script type="text/javascript" src="jquery/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/bootstrap_40.min.css">
    <script type="text/javascript" src="js/create_niko.js"></script>

  </head>

  <body>
    <div class="container" id="container">
      <div class="row">
        <div class="col-10">
            <div class="row" id="connect">
                <div class="col-1">
                </div>
                <div class="col-10" id="NikoTitle">
                    <img src='./images/bandeau.png' alt='' id="logo">
                </div>
                <div class="col-1">
                </div>
            </div>
            <div class="row">
            <div class="col-1"></div>
        		<div class="col-10" id="formulaire">
    					<form  ENCTYPE="multipart/form-data" method="post" action="">
    					  <div class="divDateDuJour">
    								<span id="DateDuJour"></span>
    						</div>
    						<div class="divDateDuJour">
    							<canvas id="canvas" width="100" height="100"></canvas>
    							<span >Comment s'est passée votre journée ?</span>
    						</div>
    						<div id="nikoImg">
    						</div>
    						<div class="nikoComment" >
    						  <textarea id="nikoComment" maxlength="160">Tapez vos commentaires ici</textarea>
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
           callCreateNiko("nikoImg");
       })
    </script>
  </body>
</html>
