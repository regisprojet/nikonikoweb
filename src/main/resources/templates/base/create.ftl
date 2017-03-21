
<head>
    <#include "../includable/bootstrap.ftl">
    <#include "../includable/jquery.ftl">
    <#include "../includable/baseFieldValidator.ftl">
</head>
<html>
<body>
    <h1>${page}</h1>
    <form id="createForm" action="" method="POST">
        <#include "../includable/formCreateContent.ftl">
        <br>
            <input type="submit" value="submit"/>
        </br>
    </form>
    <a href="list">Back</a>
</body>
</html>