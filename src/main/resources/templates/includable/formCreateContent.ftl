        <#list fields as field>
            <#list currentItem?keys as key>
                <#assign subItem = currentItem[key]>
                <#if field == key>
                    <#if field != "id">
                        <#if subItem['type'] == "Date">
                            <br>
                                ${key}
                                <input id="${key}" type="datetime" readonly
                                    name="${key}"
                                    value="" />
                                <input id="${key}date" type="date" value="">
                                <input id="${key}time" type="time" step="1" value="">
                            </br>
                        <#elseif subItem['type'] == "Long" || subItem['type'] == "Integer">
                            <br>
                                ${key}
                                <input type="number"
                                    name="${key}"
                                    value="" />
                            </br>
                        <#elseif subItem['type'] == "Boolean">
                            <br>
                                ${key}
                                <input id="${key}checkbox" type="checkbox"
                                    name="${key}checkbox"
                                    value="" />

                                <input id="${key}" type="hidden"
                                    name="${key}"
                                    value="" />
                            </br>
                        <#else>
                            <br>
                                ${key}
                                <input type="text"
                                    name="${key}"
                                    value="" />
                            </br>
                        </#if>
                    </#if>
                </#if>
            </#list>
        </#list>