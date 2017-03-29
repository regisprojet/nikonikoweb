
<script type="text/javascript">
$('#my-list').ajac("example.php")
	.done(function(data) {
		var tbody = $(this).find('tbody');
		tbody.empty();
		$(data.content).each(function(index,element) {
			console.debug(element.content);
			tbody
			  .append(
				$('<tr>')
				   .append($("<td>").html(element.id)
                   .append($("<td>").html(element.content)
                   
				);
		});
	});
	// TODO Draw button
	// TODO Call Ajax on button



