        <#list fields as field>
            <#list currentItem?keys as key>
                <#if key == field>
                    <#if key != "id">
                        <#if currentItem[key]?is_boolean>
                            <br>${key} : ${currentItem[key]?c}</br>
                                <input type="hidden" name="${key}" value="${currentItem[key]?c}">
                        <#elseif currentItem[key]?is_date>
                            <br>${key} : ${currentItem[key]?string("yyyy/MM/dd HH:mm:ss")}</br>
                                <input type="hidden" name="${key}" value="${currentItem[key]?string("yyyy/MM/dd HH:mm:ss")}">
                        <#elseif currentItem[key]?is_sequence>
                            <br>Sequence</br>
                        <#else>
                            <br>${key} : ${currentItem[key]}</br>
                                <input type="hidden" name="${key}" value="${currentItem[key]}">
                        </#if>
                    <#else>
                        <input type="hidden" name="id" value="${currentItem[key]}">
                    </#if>
                </#if>
            </#list>
        </#list>