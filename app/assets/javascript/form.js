$(document).ready(function() {

	$(".btn-start").on("click", function(e){
		$(".error").remove();
		$.ajax({
			url: "/messages/1",
			type: "GET",
			success: function(data) {
				$('#detailSection').addClass("hide");
				$('#messagesSection').removeClass("hide");
				$("#messagesContent").html(data.html);

				$("#btn-next").on("click", function(){
					$("#messagesSection").addClass("hide");
					$("#formSection").removeClass("hide");
				});

				initPage();

				
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
					if(data.error.message){
						$("#message").after("<p class='error'>say something !</p>");
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

function initPage(){

	$(".btn-more").on("click", function(){
		var page=$('#messagesSection').data('page');
		console.log(page);
		$.ajax({
			url: "/messages/"+(page+1),
			type: "GET",
			success: function(data) {
				$("#messagesContent").prepend(data.html);	
				$('#messagesSection').data('page',$('#messagesSection').data('page')+1);
			},
			error: function() {
				console.log("Error updating");
			}
		});
	});
}
