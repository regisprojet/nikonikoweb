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
						<p>Liste des</p>
						<p> utilisateurs</p>
					</div>
				</div>
			</div>
			<div class="col-xs-1"></div>
		</div>
		<div class="row" id="titleuserlist">
		    <div class="col-xs-1"></div>
			<div class="col-xs-2">matricule</div>
			<div class="col-xs-2">login</div>
			<div class="col-xs-2">prénom</div>
			<div class="col-xs-2">nom</div>
			<div class="col-xs-2"></div>
            <div class="col-xs-1"></div><br>
        </div>
		    
		<div class="row" id="userlist">
			
	    	<#if userlist??>
            <#list userlist as user>
            <#if user??> 
            <div class="col-xs-1"></div>
			<div class="col-xs-2">${user['registration_cgi']}</div>
			<div class="col-xs-2">${user['login']}</div>
			<div class="col-xs-2">${user['firstname']}</div>
			<div class="col-xs-2">${user['lastname']}</div>
			<div class="col-xs-2"><a href="/admin2/user/${user['id']}/update">modifier</a></div>
            <div class="col-xs-1"></div><br>
            </#if>
            </#list>
            </#if>
			<div class="col-xs-1"></div>
		</div>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10"  id="NikoFooter"></div>
			<div class="col-xs-1"></div>
		</div>
		<div class="row">
	     	<div class="col-xs-1"></div>
			<div class="col-xs-2"><a href="../../../admin2/user/list?limit=${limit}&offset=${prevOffset}">Précédents</a></div>
			<div class="col-xs-6"></div>
            <div class="col-xs-2"><a href="../../../admin2/user/list?limit=${limit}&offset=${nextOffset}">Suivants</a></div>
	     	<div class="col-xs-1"></div>
		</div>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10"  id="NikoFooter"></div>
			<div class="col-xs-1"></div>
		</div>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-12"  id="NikoFooter"></div>
			<div class="col-xs-1"></div>
		</div>
		<#include "ButtonBar.ftl">
	</div>

    </div>
</body>
</html>