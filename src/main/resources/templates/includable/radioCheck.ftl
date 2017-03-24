    <script>
        $(document).ready(function(){
            $(":radio","#associateForm" ).each(function() {
                $(this).change(function() {
                    if($(this).attr("checked") == "checked"){
                        $("#idLinked").val($(this).val());
                    }
                });
            });
        });
    </script>