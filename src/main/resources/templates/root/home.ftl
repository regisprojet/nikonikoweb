<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <#include "../includable/bootstrap.ftl">
    <#include "../includable/jquery.ftl">
    <link rel="stylesheet" type="text/css" href="/css/custom.css"/>
</head>
<body>
    <h1> Bienvenue ${username} </h1>
 
     <form action="home" method="POST">
   
        <input type='text' name='humeur' value=""/>

        <input type="hidden"
            name="${_csrf.parameterName}"
            value="${_csrf.token}"/>
        <input name="submit" type="submit">
  

    </form>
 
</body>