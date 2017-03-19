
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/custom.css"/>
</head>
<html>
<body>
    <h1>${page}</h1>
    <table class="table table-bordered table-hover">
        <#list currentItem?keys as key>
            <#if key != "id">
                <tr>
                    <th>${key}</th>
                    <#if currentItem[key]?is_boolean>
                        <td>${currentItem[key]?c}</td>
                    <#elseif currentItem[key]?is_sequence>
                        <td>Sequence</td>
                    <#elseif currentItem[key]?is_date_like>
                        <td>${currentItem[key]?string("yyyy/MM/dd HH:mm:ss")}</td>
                    <#else>
                        <td>${currentItem[key]}</td>
                    </#if>
                </tr>
            </#if>
        </#list>
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