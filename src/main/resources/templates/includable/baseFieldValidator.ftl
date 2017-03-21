    <script>
        $(document).ready(function(){
        <#list currentItem?keys as key>
            <#assign subItem = currentItem[key]>
            <#if subItem['type'] == "Date">
                $("#${key}date").change(function(){
                    $("#${key}").val($("#${key}date").val().replace(/\-/g,"/") + " " + $("#${key}time").val());
                });
                $("#${key}time").change(function(){
                    $("#${key}").val($("#${key}date").val().replace(/\-/g,"/") + " " + $("#${key}time").val());
                });
            <#elseif subItem['type'] == "Boolean">
                $("#${key}checkbox").change(function(){
                    $("#${key}").val($("#${key}checkbox").is(':checked') ? "true" : "false");
                });
                $("#${key}").val("false");
            </#if>
        </#list>
            $( "#createForm" ).submit(function( event ) {
                $("#createForm").each(function(index) {
                    $(this).children().each (function() {
                        if($(this).val() == ""){
                            $(this).prop('disabled', true);
                        }
                    });
                });
            });
        });
    </script>