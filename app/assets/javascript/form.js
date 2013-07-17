$(document).ready(function() {

	$(".btn-start").on("click", function(e){
		$(".error").remove();
		$.ajax({
			url: "/messages",
			type: "GET",
			success: function(data) {
				$('#detailSection').addClass("hide");
				$('#messagesSection').removeClass("hide");
				$("#messagesSection").html(data.html);

				$("#btn-next").on("click", function(){
					$("#messagesSection").addClass("hide");
					$("#formSection").removeClass("hide");
				});
			},
			error: function() {
				console.log("Error updating");
			}
		});
	});

	$(".btnSubmit").on("click", function(e){
		$(".error").remove();
		$.ajax({
			url: "/",
			type: "POST",
			data: $("#form").serializeArray(),
			success: function(data) {
				if(data.error!=null){
					if(data.error.name){
						$("#name").after("<p class='error'>"+data.error.name+"</p>");
					}
					if(data.error.mail){
						$("#mail").after("<p class='error'>"+data.error.mail+"</p>");
					}
					if(data.error.lat || data.error.lon){
						$("#demo").after("<p class='error'> your location isn't available</p>");
					}
				} else{
					$("#formSection").addClass("hide");
					$("#feedbackSection").removeClass("hide");
					$("#shareSection").removeClass("hide");
				}

			},
			error: function() {
				console.log("Error updating");
			}
		});
	});
});

