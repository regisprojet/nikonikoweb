<!DOCTYPE html>
<#include "Head.ftl">
<html>
<#include "BodyBegin.ftl">
		
	
	

		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-5">
				<#if userItem??>
				<label class="userField">matricule</label>
				<input type="text" name="matricule" value="${userItem['registration_cgi']}" /><br>
				<label class="userField">prénom</label>
				<input type="text" name="firstname" value="${userItem['firstname']}" /><br>
				<label class="userField">nom</label>
				<input type="text" name="lastname" value="${userItem['lastname']}" /><br>
				<label class="userField">login</label>
				<input type="text" name="login" value="${userItem['login']}" /><br>
				<label class="userField">agence</label>
				<input type="text" name="agency" value="${agencyName}" /><br>
				<label class="userField">verticale</label>
				<input type="text" name="pole" value="${poleName}" /><br>
				</#if>
			
			<div class="col-xs-6">
		</div>
		
	
		
    </div>

<#include "BodyEnd.ftl">
</html>