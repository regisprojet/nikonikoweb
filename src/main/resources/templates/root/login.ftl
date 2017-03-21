<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <#include "../includable/bootstrap.ftl">
    <#include "../includable/jquery.ftl">
    <link rel="stylesheet" type="text/css" href="/css/custom.css"/>
</head>
<body>
    <h1> login </h1>

 <div class="formulaire">
    <form action="" method="post">
     <fieldset>
         <legend>Utilisateur</legend>
         <label for="login">login</label>
         <input name="login" id="login">
         <label for="password">mot de passe</label>
         <input name="password" id="password" type="password">

        <input type="submit" value="Go!">
      </fieldset>
    </form>
  </div>


</body>


