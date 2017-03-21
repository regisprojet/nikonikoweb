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
                        <#if linkedItems?has_content>
                            <#if linkedItems?seq_contains(item['id'])>
                                <input id="${item['id']}" type="checkbox" checked
                                    name="ids[]"
                                    value="${item['id']}" />
                            <#else>
                                <input id="${item['id']}" type="checkbox"
                                    name="ids[]"
                                    value="${item['id']}" />
                            </#if>
                        <#else>
                            <input id="${item['id']}" type="checkbox"
                                name="ids[]"
                                value="${item['id']}" />
                        </#if>
                    </td>
                </tr>
            </#list>
            <tr>
                <td>
                    <input id="0" type="hidden"
                        name="ids[]"
                        value="0" />
                    <input type="submit" value="Validate"/>
                </td>
            </tr>
        </form>
    </table>