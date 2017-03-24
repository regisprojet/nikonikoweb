
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<html>
<body>
    <h1>${page}</h1>
    <table class="table table-bordered table-hover">
        <#include "../includable/formShowContent.ftl">
    </table>
    <#include "../includable/associationMultiTable.ftl">
    <br>
        <a href="../index">Back</a>
    </br>
</body>
</html>