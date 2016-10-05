$(document).ready(function() {
	$.ajax({
		url : "testJB.xml",
		cache : false,
		dataType : "xml",
		success : function(xml) {
			$("#list_tren").empty();

			$(xml).find("workout").each(function() {
				var name = $(this).find("name:first").text();
				var element = "<div id='tren'><button id='edite_tren'>Редактировать</button><button class='delete'>Удалить</button><span id='name_tren'>" + name + "</span></div>";
				$("#list_tren").append(element);
				$(".delete").click(deleting);
			});
		}
	});
});

