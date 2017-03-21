
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<html>
<body>
    <h1>${page}</h1>
    <form action="" method="POST">
        <#include "../includable/formDeleteContent.ftl">
        <br>
            <input type="submit" value="delete"/>
        </br>
    </form>
    <a href="../list">Back</a>
</body>
</html>