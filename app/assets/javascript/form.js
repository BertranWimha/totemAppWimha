$(document).ready(function() {

	$(".btn-start").on("click", function(e){
		$(".error").remove();
		$.ajax({
			url: "/messages/0",
			type: "GET",
			success: function(data) {
				$('#detailSection').addClass("hide");
				$('#messagesSection').removeClass("hide");
				$("#messagesContent").html(data.html);

				$("#btn-next").on("click", function(){
					$("#messagesSection").addClass("hide");
					$("#formSection").removeClass("hide");
				$('html,body').scrollTop(0);
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
					$('html,body').scrollTop(0);
					$("#formSection").addClass("hide");
					$("#feedbackSection").removeClass("hide");
					$("#shareSection").removeClass("hide");
					$('#mapSection').removeClass("hide");
					$('#feedbackMessage').html($('#message').val());
					$('#feedbackName').html($('#name').val());
					$('#feedbackTimeStamp').html(data.timestamp);
					//$('.twitter-share-button').attr('data-text',$('#message').val());
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
				$("#messagesContent").append(data.html);	
				$('#messagesSection').data('page',$('#messagesSection').data('page')+1);  
				$("html, body").animate({ scrollTop: $(document).height() }, "slow");

			},
			error: function() {
				console.log("Error updating");
			}
		});
	});
}
