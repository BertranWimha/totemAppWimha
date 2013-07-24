$(document).ready(function() {

  $('.btn-loc').click(function(){
    console.log('enter getloc');


    getLocation();
  });
});

    function getLocation(){
      var x=$("#demo");
      if (navigator.geolocation){
        console.log('navigator ok');
        navigator.geolocation.getCurrentPosition(showPosition);
      }else{
        console.log('navigator notok');
        x.innerHTML="Geolocation is not supported by this browser.";
      }
    }

    function showPosition(position){
      var lat = position.coords.latitude; 
      var lon = position.coords.longitude;
      var x=$("#demo");
      console.log(position);
      $("#lat").attr('value',lat);
      $("#lon").attr('value',lon);
      x.innerHTML="MyLatitude: " + position.coords.latitude +
      "<br>MyLongitude: " + position.coords.longitude;
   
      $.ajax({
        url: "/address/"+lat+"/"+lon,
        type: "GET",
        success: function(data) {
          $("#demo").html(data.address);
          $("#feedbackLoc").html(data.address);
          $("#demo").show();
        },
        error: function() {
        }
      }); 

    }
