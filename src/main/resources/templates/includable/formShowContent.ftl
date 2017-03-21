        <#list currentItem?keys as key>
            <#if key != "id" && !currentItem[key]?is_sequence>
                <tr>
                    <th>${key}</th>
                    <#if currentItem[key]?is_boolean>
                        <td>${currentItem[key]?c}</td>
                    <#elseif currentItem[key]?is_date_like>
                        <td>${currentItem[key]?string("yyyy/MM/dd HH:mm:ss")}</td>
                    <#else>
                        <td>${currentItem[key]}</td>
                    </#if>
                </tr>
            </#if>
        </#list>