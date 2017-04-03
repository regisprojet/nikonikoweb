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
						<p>rechercher</p>
						<p>des utilisateurs</p>
					</div>
				</div>
			</div>
			<div class="col-xs-1"></div>
		</div>
	    <div class="row">
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
		
	</div>

    </div>
</body>
</html>