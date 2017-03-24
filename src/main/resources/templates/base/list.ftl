
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<body>
    <h1> ${page} </h1>
    <a href="create">Create new</a>
    <table class="table table-bordered table-hover">
        <tr>
            <#list items as item>
               <#if fields??>
                <#list fields as field>
                <#if field??>
                     <#list item?keys as key>
                     <#if key??>
                        <#if field == key>
                            <th>${key}</th>
                        </#if>
                    </#if>
                    </#list>
               </#if>
               </#list>
                <#else>
                           <script type="text/javascript"> console.log("erreur de fields "+${fields})  </script>
                </#if>
                <#break>
            </#list>
        </tr>

        <#list items as item>
            <tr>
            	<#if fields??>
               <#list fields as field>
                    <#if field??> 
                    <#list item?keys as key>
                        <#if key??>
                        <#if field == key>
                            <#if item[key]?is_boolean>
                                <td>${item[key]?c}</td>
                            <#elseif item[key]?is_date_like>
                                <td>${item[key]?string("yyyy/MM/dd HH:mm:ss")}</td>
                            <#else>
                                <td>${item[key]}</td>
                            </#if>
                        
                        </#if>
                         <#else>
                           <script type="text/javascript"> console.log("erreur "+${field})  </script>
                        </#if>
                    </#list>
                    </#if>
                </#list>
                <td><a href="${item["id"]}/show">Select</a></td>
                <td>
                    <form action="${item["id"]}/delete" method="POST">
                        <input type="hidden" name="id" value="${item["id"]}">
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
            </#if>
        </#list>
    </table>
</body>