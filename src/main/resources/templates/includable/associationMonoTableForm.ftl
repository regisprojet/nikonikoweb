    <table class="table table-bordered table-hover">
        <tr>
            <#list items as item>
                <#list fields as field>
                    <#list item?keys as key>
                        <#if field == key && key != "id">
                            <th>${key}</th>
                        </#if>
                    </#list>
                </#list>
                <#break>
            </#list>
        </tr>

        <#if items?has_content>
        <form id="associateForm" action="" method="POST">
            <#list items as item>
                <tr>
                    <#list fields as field>
                        <#list item?keys as key>
                            <#if field == key>
                                <#if key != "id">
                                    <#if item[key]?is_boolean>
                                        <td>${item[key]?c}</td>
                                    <#elseif item[key]?is_date_like>
                                        <td>${item[key]?string("yyyy/MM/dd HH:mm:ss")}</td>
                                    <#else>
                                        <td>${item[key]}</td>
                                    </#if>
                                </#if>
                            </#if>
                        </#list>
                    </#list>
                    <td>
                        <#if linkedItem??>
                            <#if item['id'] == linkedItem>
                                <input id="${item['id']}" type="radio" checked
                                    name="idLinked"
                                    value="${item['id']}" />
                            <#else>
                                <input id="${item['id']}" type="radio"
                                    name="idLinked"
                                    value="${item['id']}" />
                            </#if>
                        <#else>
                            <input id="${item['id']}" type="radio"
                                name="idLinked"
                                value="${item['id']}" />
                        </#if>
                    </td>
                </tr>
            </#list>
            <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
            <tr>
                <td>
                    <input type="submit" value="Validate"/>
                </td>
            </tr>
        </form>
        <#else>
            No items founded.
        </#if>
    </table>