    <#if linkedItem??>
        <table class="table table-bordered table-hover">
            <tr>
                    <#list fields as field>
                        <#list linkedItem?keys as key>
                            <#if field == key && key != "id">
                                <th>${key}</th>
                            </#if>
                        </#list>
                    </#list>
            </tr>

                    <tr>
                        <#list fields as field>
                            <#list linkedItem?keys as key>
                                <#if field == key>
                                    <#if key != "id">
                                        <#if linkedItem[key]?is_boolean>
                                            <td>${linkedItem[key]?c}</td>
                                        <#elseif linkedItem[key]?is_date_like>
                                            <td>${linkedItem[key]?string("yyyy/MM/dd HH:mm:ss")}</td>
                                        <#else>
                                            <td>${linkedItem[key]}</td>
                                        </#if>
                                    </#if>
                                </#if>
                            </#list>
                        </#list>
                    </tr>
        </table>
    <#else>
        <br>
            Empty association
        </br>
    </#if>