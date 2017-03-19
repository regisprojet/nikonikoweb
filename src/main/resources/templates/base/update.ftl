
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/custom.css"/>

    <script src="http://code.jquery.com/jquery-3.0.0.min.js"></script>

    <script>
        $(document).ready(function(){
        <#list currentItem?keys as key>
            <#if currentItem[key]?is_datetime>
                $("#${key}date").change(function(){
                    $("#${key}").val($("#${key}date").val().replace(/\-/g,"/") + " " + $("#${key}time").val());
                });
                $("#${key}time").change(function(){
                    $("#${key}").val($("#${key}date").val().replace(/\-/g,"/") + " " + $("#${key}time").val());
                });
            </#if>
        </#list>
        });
    </script>

</head>
<html>
<body>
    <h1>${page}</h1>
    <form action="" method="POST">
        <#list fields as field>
            <#list currentItem?keys as key>
                <#if key == field>
                    <#if key != "id">
                        <#if currentItem[key]?is_boolean>
                            <br>${key} :
                                <input type="text" name="${key}" value="${currentItem[key]?c}">
                            </br>
                        <#elseif currentItem[key]?is_sequence>
                            <br>Sequence</br>
                        <#elseif currentItem[key]?is_datetime>
                            <br>${key} :
                                <input id="${key}" type="datetime" name="${key}" readonly value="${currentItem[key]?string("yyyy/MM/dd HH:mm:ss")}">
                                <input id="${key}date" type="date" value="${currentItem[key]?string("YYYY-MM-DD")}">
                                <input id="${key}time" type="time" value="${currentItem[key]?time}">
                            </br>
                        <#else>
                            <br>${key} :
                                <input type="text" name="${key}" value="${currentItem[key]}">
                            </br>
                        </#if>
                    <#else>
                        <input type="hidden" name="id" value="${currentItem[key]}">
                    </#if>
                </#if>
            </#list>
        </#list>

        <br>
            <input type="submit" value="update"/>
        </br>
    </form>
    <a href="../list">Back</a>
</body>
</html>