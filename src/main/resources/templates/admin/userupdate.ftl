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
		
	
	
		
		<form id="" action="/admin2/user/${userItem['id']}/update" method="POST" modelAttribute="userForm" >
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-5">
			<#if userItem??>
				<label class="userField">matricule</label>
				<input type="text" class="userInputField" name="registration" value="${userItem['registration_cgi']}" /><br>
				<label class="userField">prénom</label>
				<input type="text" class="userInputField" name="firstname" value="${userItem['firstname']}" /><br>
				<label class="userField">nom</label>
				<input type="text" class="userInputField" name="lastname" value="${userItem['lastname']}" /><br>
				<label class="userField">login</label>
				<input type="text" class="userInputField" name="login" value="${userItem['login']}" /><br>
				<label class="userField">verticale</label>
				<select class="userInputField"  name="pole" size="1">
					<option>non défini</option>
					<#list poles as pole>
						<#if "${pole.name}"  ==  "${poleName}">
							<option selected>${pole.name}</option>
						<#else>
							<option>${pole.name}</option>
						</#if>
					</#list>
				</select><br>
				<label class="userField">agence</label>
				<select class="userInputField" name="agency" size="1">
					<option>non défini</option>
					<#list agencies as agency>
						<#if "${agency.name}"  ==  "${agencyName}">
							<option selected>${agency.name}</option>
						<#else>
							<option>${agency.name}</option>
						</#if>
					</#list>
				</select><br>
				
			</div>
			<#if false>		
			<div class="col-xs-5>
				<label class="userField">fonctions</label>
				<select multiple class="userInputField"  name="role" size="4">
					<#list roles as role>
						<#if "${role.role}"  ==  "ROLE_USER">
							<option selected>${role.role}</option>
						<#else>
							<option>${role.role}</option>
						</#if>
					</#list>
				</select><br>
			</div>
			<#elseif false>
			<div class="col-xs-5>		
					<label></label>
					<label class="userField">fonctions</label><br>
					<#list roles as role>
						<#if "${role.role}"  ==  "ROLE_USER">
							<input checked type="checkbox" name="${role.role}">${role.role}</input><br>
						<#else>
							<input type="checkbox" name="${role.role}">${role.role}</input><br>
						</#if>
					</#list>
			</div>		
	        <#else>
	        <div class="col-xs-2">
				<div class="roleContainer" id="roleTarget">
					<#if userRoles??>
					<#list userRoles as role>
						
						 <#if "role.role" == "ROLE_ADMIN">
						 
<img class="roleDaD" id="iconAdmin" src="/images/admin_40.png">
 <#elseif "role.role" == "ROLE_USER">
 <img class="roleDaD" id="iconUser" src="/images/user_40.png">
 <#elseif "role.role" == "ROLE_VIP">
 <img class="roleDaD" id="iconVip" src="/images/vip_40.png">
 <#elseif "role.role" == "ROLE_PROJECT_LEADER">
  <img class="roleDaD" id="iconProjectLeader" src="/images/project_leader_40.png">
 </#if>
						
						
					</#list>
					</#if>
				</div>
			</div>
			<div class="col-xs-1">
	        <div class="col-xs-2">
				<div class="roleContainer" id="roleSource">
				<#if false>
				
    				  <img class="roleDaD" id="iconUser" src="/images/user_40.png">
    				  <img class="roleDaD" id="iconVip" src="/images/vip_40.png">
    				  <img class="roleDaD" id="iconAdmin" src="/images/admin_40.png">
				      <img class="roleDaD" id="iconProjectLeader" src="/images/project_leader_40.png">
				      
				 <#else>
				<#if roles??>
					<#list roles as role>
				   	<#if "${role.role}" == "ROLE_ADMIN">
						 <img class="roleDaD" id="iconAdmin" src="/images/admin_40.png">
 					<#elseif "${role.role}"  == "ROLE_USER">
 						<img class="roleDaD" id="iconUser" src="/images/user_40.png">
 					<#elseif "${role.role}"  == "ROLE_VIP">
 						<img class="roleDaD" id="iconVip" src="/images/vip_40.png">
 					<#elseif "${role.role}" == "ROLE_PROJECT_LEADER">
  						<img class="roleDaD" id="iconProjectLeader" src="/images/project_leader_40.png">
 					</#if>
					</#list>
				</#if>
				
				 </#if>
				</div>
			</div>
	          								
			</#if>
			<#else>
				<div class="col-xs-5"></div>		
			</#if>
			<div class="col-xs-1"></div>
		</div><!--div row-->
		<input type="submit" value="submit"/>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-10"  id="NikoFooter"></div>
			<div class="col-xs-1"></div>
		</div>
		<#include "ButtonBar.ftl">
		
		
    <!-- Lancement des scripts -->
 	<script src="../../../js/RoleDragAndDrop.js">
    </script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
    <script>
		$(document).ready(function() {
			enableDragAndDrop();
       })
    </script>


<#include "BodyEnd.ftl">
</html>
