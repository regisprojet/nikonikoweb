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
              <div class="col-12">
                <div id="calendar"></div>
              </div>
            </div>
            <div class="row">
              <div class="col-1"></div>
              <div class="col-10"  id="NikoFooter"></div>
              <div class="col-1"></div>
            </div>
        </div>
        <div class="col-2 rightArea">
         		<div id="inputMenue">
	              	<a href="input.html">
	              	<img src='./images/vote.png' alt='' class="imgMenue"></a>
	            </div>
	            <div id="consultMenue">
	              	<a href="calendrier.html" target="_blank">
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
    <script type="text/javascript" src="js/create_niko.js"></script>
    <script type="text/javascript" src="js/function.js"> </script>
    <script>
       $(document).ready(function() {
            createWeek(new Date());
       })
    </script>
  </body>
</html>
