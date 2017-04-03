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
						<p>modification</p>
						<p>utilisateur</p>
					</div>
				</div>
			</div>
		</div>
		
	
		<div class="row">
			<div class="col-xs-2">
			<#list fieldList as field>
			 <label>${dictFr[field]}<br>	
			</#list>
			</div>
			
			
			<div class="col-xs-2">
			<#list fieldList as field>
			<input type="text" name="${field}" value=""/>
			</#list>
			</div>
			<div class="col-xs-8">
			
		</div>
		<#include "ButtonBar.ftl">
    </div>
</body>
</html>