
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
                <#list currentItem?keys as key>
                    <#assign subItem = currentItem[key]>
                    <#if subItem['ManyToMany']?? || subItem['OneToMany']??>
                        <td>
                            <a href="${item["id"]}/${subItem['name']?lower_case}">${subItem['name']?lower_case}</a>
                            <br>
                            <a href="${item["id"]}/${subItem['name']?lower_case}link">Associate ${subItem['name']?lower_case}</a>
                        </td>
                    </#if>
                    <#if subItem['ManyToOne']?? || subItem['OneToOne']??>
                        <td>
                            <a href="${item["id"]}/${subItem['name']?lower_case}">${subItem['name']?lower_case}</a>
                            <br>
                            <a href="${item["id"]}/${subItem['name']?lower_case}link">Associate ${subItem['name']?lower_case}</a>
                        </td>
                    </#if>
                </#list>

                <td>
                    <form action="${item["id"]}/delete" method="POST">
                        <input type="hidden" name="id" value="${item["id"]}">
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
    <br>
        <a href="../">Back</a>
    </br>
</body>