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
        		<form action="" method="post">
         		  <div id="connectezVous">
              				<span >Connectez-vous :</span>
            			</div>
            			<div class="formField">
              				<span >Utilisateur :</span>
            			</div>
            			<div class="inputText">
              				<input  type="texte" name="login" id="login">
            			</div>
            			<div class="formField">
              				<span >Mot de passe :</span>
            			</div>
            			<div class="inputText">
              				<input type="password" name="password"  id="password" >
            			</div>
            			<input type="submit" value="Go!">
          		</form>
        		</div>
            <div class="col-1"></div>
      		</div>
        </div>
      </div>
    </div>


    <!-- Lancement des scripts -->
    <!--#######################-->
    <script type="text/javascript" src="js/function.js">
    </script>
    <!--script>
       $(document).ready(function() {
            main2();
       })
       </script-->
  </body>
</html>
