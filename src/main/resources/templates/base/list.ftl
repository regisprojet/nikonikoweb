
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<body>
    <h1> ${page} </h1>
    <a href="create">Create new</a>
    <table class="table table-bordered table-hover">
        <tr>
            <#list items as item>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if field == key>
                            <th>${key}</th>
                        </#if>
                    </#list>
                </#list>
                <#break>
            </#list>
        </tr>

        <#list items as item>
            <tr>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if field == key>
                            <#if item[key]?is_boolean>
                                <td>${item[key]?c}</td>
                            <#elseif item[key]?is_date_like>
                                <td>${item[key]?string("yyyy/MM/dd HH:mm:ss")}</td>
                            <#else>
                                <td>${item[key]}</td>
                            </#if>
                        </#if>
                    </#list>
                </#list>
                <td><a href="${item["id"]}/show">Select</a></td>
                <td>
                    <form action="${item["id"]}/delete" method="POST">
                        <input type="hidden" name="id" value="${item["id"]}">
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
</body>