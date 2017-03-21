
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<html>
<body>
    <h1>${page}</h1>
    <table class="table table-bordered table-hover">
        <#include "../includable/formShowContent.ftl">
        <tr>
            <td><a href="update">Update</a></td>
        </tr>
        <tr>
            <td><a href="delete">Delete</a></td>
        </tr>
        <tr>
            <td><a href="../list">Back</a></td>
        </tr>

    </table>
</body>
</html>