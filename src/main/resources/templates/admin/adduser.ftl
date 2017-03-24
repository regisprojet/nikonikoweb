<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Exemples JavaScript</title>
    <meta name="viewport" content="initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="css/nikoniko_regis_denis.css" rel="stylesheet" >

    <link rel="stylesheet" href="bootStrap/bootstrap_3.min.css">
    <#include "../includable/jquery.ftl">

</head>

<html>
<body>
    <h1> ${page} </h1>
    <div class="container" id="container">
		<div class="row">
			<form id="createForm" action="" method="POST" modelAttribute="userForm" >
               <div class="col-xs-4">
  				
        			
       				 <#include "../includable/formCreateContent.ftl"> 
	    			
	    			
    			
    			<a href="list">Back</a>
            </div>
                   <div class="col-xs-4">
    <ul>
    <#if functionNames??>
    <#list functionNames as name>
        <#if name??> 
        <li>
        <!-- <input type="checkbox" name="${name}" value="">${name}<br> -->
        ${name}
        </li>
        </#if>
    </#list>
    </#if>
     </ul> 
                   </div>

            <input type="submit" value="submit"/>
</form>
              </div>
    </div>
</body>
</html>