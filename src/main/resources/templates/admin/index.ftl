<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Exemples JavaScript</title>
    <meta name="viewport" content="initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="../../../css/nikoniko_regis_denis.css" rel="stylesheet" >

    <link rel="stylesheet" href="../../../bootStrap/bootstrap_3.min.css">
    <#include "../../../includable/jquery.ftl">

</head>

<html>
<body>

  <body>
	<canvas id="canvas" width="25" height="25"></canvas>
	<div class="container" id="container">
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10">
				<div class="row NikoInputTitle">
					<div class="col-xs-8 ">
					   <img src='./../../../images/bandeau.png' alt='' id="logo">
					</div>
					<div class="col-xs-4 ">
						<p>pages</p>
						<p>administration</p>
					</div>
				</div>
			</div>
			<div class="col-xs-1"></div>
		</div>
	    <div class="row" id="titleuserlist">
			<div class="col-xs-3"></div>
			<div class="col-xs-6">
				<a href="user/list?limit=7&off	set=0">liste des utilisateurs</a><br>
				<a href="searchuser">recherche d'un utilisateur</a><br>

				<a href="user/list?limit=7&offset=0">liste des roles</a><br>
				<a href="user/list?limit=7&offset=0">liste des agences</a><br>
			</div>
			<div class="col-xs-3"></div>
		</div>

		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10"  id="NikoFooter"></div>
			<div class="col-xs-1"></div>
		</div>
		<div class="row">
		    <div class="col-xs-8"></div>
		    <div class="col-xs-1">
		    	<#list currentUserRoles as role>
					<#if role.role == "ROLE_USER">
						<form action="/inputNiko" method="get" id="adminNiko">
							<div id="adminNikoDiv">
								<input type="submit" class="menu_button" id="inputNiko" value="">
					 		</div>
						</form>
				    </#if>
				</#list>
			</div>
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
			<div class="col-xs-1"></div>
		</div>
    </div>
</body>
</html>