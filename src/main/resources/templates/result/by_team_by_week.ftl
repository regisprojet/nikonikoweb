<!DOCTYPE html>
<html lang="en">
  <head>
	<meta charset="utf-8">
	<title>Exemples JavaScript</title>
	<meta name="viewport" content="initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<link href="../../../css/nikoniko_regis_denis.css" rel="stylesheet" >

	<#include "../../../includable/bootstrap.ftl">
	<#include "../../../includable/jquery.ftl">
	  <script type="text/javascript" src="../../../js/histogram.js"></script>
  </head>

  <body>
	<canvas id="canvas" width="25" height="25"></canvas>
	<div class="container" id="container">
		<div class="row" >
			<div class="col-xs-1"></div>
			<div class="col-xs-10">
				<div class="row NikoInputTitle">
					<div class="col-xs-7 ">
					   <img src='./../../../images/bandeau.png' alt='' id="logo">
					</div>
					<div class="col-xs-2 " id="VerticaleEquipeTitre">
						<p id="verticaleTitle">Verticale:</p>
						<p id="teamName">Equipe:</p>
					</div>
					<div class="col-xs-3 " id="VerticaleEquipeName">
						<p id="verticaleTitle">${verticale}</p>
						<p id="teamTitle">${equipe}</p>
					</div>
				</div>
			</div>
			<div class="col-xs-1"></div>
		</div>
		<div class="row"  id="titleuserlist">
			<div class="col-xs-1"></div>
			<div class="col-xs-10">
           		 <div id="bargraph"></div>
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
				<form action="graphWeekInputNiko" method="post" id="inputNiko" >
					<div id="voteMenue">
						<input type="submit" class="menu_button" id="inputNiko" value="">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</div>
				</form>
			</div>
			<div class="col-xs-1">
				<form action="graphWeekCalendar" method="post" id="restitution" >
					<div id="restMenue">
						<input type="submit" class="menu_button" id="restitution" value="">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</div>
				</form>
			</div>
			<div class="col-xs-1">
				<form action="graphWeekLogout" method="post" id="deconnexion">
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
	<script type="text/javascript">
    var d = new Date();
    var n = d.getDay();

    var day_french_name = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];


    var bargraph = document.getElementById("bargraph");

    var span1 = document.createElement("span");

    bargraph.appendChild(span1);


    for(var i=0;i<7;i++) {
      var span = document.createElement("span");
      //var spanText = document.createElement("span");
      //spanText.innerText=day_french_name[i];
      //span.appendChild(spanText);

      var canvas = document.createElement("canvas");
      canvas.setAttribute("id","canvas"+(i+1));
      canvas.setAttribute("width","77");
      canvas.setAttribute("height","200");
      span.appendChild(canvas);
      span1.appendChild(span);
    }

    draw_histogram_week(${greens[0]},${yellows[0]},${reds[0]}, "canvas1" , day_french_name[0],0);
    draw_histogram_week(${greens[1]},${yellows[1]},${reds[1]}, "canvas2" , day_french_name[1],1);
    draw_histogram_week(${greens[2]},${yellows[2]},${reds[2]},  "canvas3" , day_french_name[2],2);
    draw_histogram_week(${greens[3]},${yellows[3]},${reds[3]}, "canvas4" , day_french_name[3],3);
    draw_histogram_week(${greens[4]},${yellows[4]},${reds[4]}, "canvas5" , day_french_name[4],4);
    draw_histogram_week(${greens[5]},${yellows[5]},${reds[5]}, "canvas6" , day_french_name[5],5);
    draw_histogram_week(${greens[6]},${yellows[6]},${reds[6]}, "canvas7" , day_french_name[6],6);
  </script>

  </body>
</html>