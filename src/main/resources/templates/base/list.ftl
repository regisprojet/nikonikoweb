
<head>
    <!--script type="text/javascript" src="jquery/jquery-3.1.1.min.js"></script-->
    <#include "../includable/bootstrap.ftl">
    <#include "../includable/jquery.ftl">
</head>
<body>
    <h1> ${page} </h1>
    <div><a href="create">Create new</a></div>
    <div>
	<#list currentUserRoles as role>
		<#if role.role == "ROLE_USER">
			<div><a href="/inputNiko">Go Back NikonikoInput</a></div>
	    </#if>
	</#list>
	</div>

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