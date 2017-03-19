<#function ordered fields datas>
    <#local result={}>
    <#list datas as data>
        <#list fields as field>
            <#if data[field]??>
                <#local result=result + {
                    field : data[field]
                }>
            </#if>
        </#list>
    </#list>

    <#return returnValue>

</#function>