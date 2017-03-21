        <#list fields as field>
            <#list currentItem?keys as key>
                <#assign subItem = currentItem[key]>
                <#if key == field>
                    <#if key != "id">
                        <#if subItem['type']?is_sequence>
                            <br>Sequence</br>
                        <#elseif subItem['type'] = "Date">
                            <br>${key} :
                                <#if subItem['value']?is_date>
                                    <input id="${key}" type="datetime" name="${key}" readonly value="${subItem['value']?string("yyyy/MM/dd HH:mm:ss")}">
                                    <input id="${key}date" type="date" value="${subItem['value']?string("YYYY-MM-DD")}">
                                    <input id="${key}time" type="time" value="${subItem['value']?time}">
                                <#else>
                                    <input id="${key}" type="datetime" name="${key}" readonly value="">
                                    <input id="${key}date" type="date" value="">
                                    <input id="${key}time" type="time" value="">
                                </#if>

                            </br>
                        <#elseif subItem['type'] == "Long" || subItem['type'] == "Integer">
                            <br>${key} :
                                <input type="number" name="${key}" value="${subItem['value']}">
                            </br>
                        <#elseif subItem['type'] = "Boolean">
                            <br>${key} :
                                <input id="${key}checkbox" type="checkbox"
                                    name="${key}checkbox" value="${subItem['value']?c}"
                                    <#if subItem['value']?c = "true"> checked="true"</#if>>
                                <input id="${key}" type="hidden" name="${key}" value="${subItem['value']?c}}">
                            </br>
                        <#else>
                            <br>${key} :
                                <input type="text" name="${key}" value="${subItem['value']}">
                            </br>
                        </#if>
                    <#else>
                        <input type="hidden" name="id" value="${subItem['value']}">
                    </#if>
                </#if>
            </#list>
        </#list>