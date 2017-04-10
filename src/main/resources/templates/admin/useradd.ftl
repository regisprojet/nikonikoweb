<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Exemples JavaScript</title>
    <meta name="viewport" content="initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="../../../css/nikoniko_regis_denis.css" rel="stylesheet" >

    <link rel="stylesheet" href="../../../bootStrap/bootstrap_3.min.css">

</head>
<html>
<script "../../../jquery/jquery-3.1.1.min.js"></script>
<#include "BodyBegin.ftl">

		<form id="" action="/admin2/useradd" method="POST" modelAttribute="userForm" >
		<div class="row titleuser">
			<div class="col-xs-1"></div>
			<div class="col-xs-5">
				<label class="userField">matricule</label>
				<input type="text" class="userInputField" name="registration" value="" /><br>
				<label class="userField">prénom</label>
				<input type="text" class="userInputField" name="firstname" value="" /><br>
				<label class="userField">nom</label>
				<input type="text" class="userInputField" name="lastname" value="" /><br>
				<label class="userField">login</label>
				<input type="text" class="userInputField" name="login" value="" /><br>
				<label class="userField">verticale</label>
				<select class="userInputField"  name="pole" size="1">
					<option>non défini</option>
					<#list poles as pole>
							<option>${pole.name}</option>
					</#list>
				</select><br>
				<div>
				<label class="userField">agence</label>
				<select class="userInputField" name="agency" size="1">
					<option>non défini</option>
					<#list agencies as agency>
							<option>${agency.name}</option>
					</#list>
				</select><br>
                </div>
                <div>
				<label id="labelPageAdminTeam" class="userField" >équipe</label>
				<select multiple class="userInputField"  name="teams" size="5">
					<#list teams as team>
							<option>${team.name}</option>
					</#list>
				</select><br>
				</div>
			</div>
			
	        <div class="col-xs-2">
				fonctions utilisateur
				<div class="roleContainer" id="roleTarget">
				</div>
			</div>
			<div class="col-xs-1">
				<div dropzone="move" id="trashZone">
				 	<img dropzone="move" id="iconTrash" src="/images/trash_40.png">
				 </div>
			</div>
	        <div class="col-xs-2">
				fonctions disponibles
				<div class="roleContainer" id="roleSource">
				
				<#if roles??>
					<#list roles as role>
				   		<#include "DrawIconRole.ftl">
					</#list>
				</#if>
				</div>
			</div>
			<div class="col-xs-1"></div>
		</div>
		<div class="titleuser">
			<input id="hybrid" type="hidden" value="" name="role" />
			<input type="submit" value="valider"/>
		</div>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10"  id="NikoFooter"></div>
			<div class="col-xs-1"></div>
		</div>
		<#include "ButtonBar.ftl">


    <!-- Lancement des scripts -->
 	<script src="/js/RoleDragAndDrop.js">
    </script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
    <script>
		$(document).ready(function() {
			enableDragAndDrop();
       })
    </script>


<#include "BodyEnd.ftl">
</html>
