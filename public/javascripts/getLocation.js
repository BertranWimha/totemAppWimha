
var x=$("#demo");

function getLocation(){
  if (navigator.geolocation){
    navigator.geolocation.getCurrentPosition(showPosition);
  }else{
    x.innerHTML="Geolocation is not supported by this browser.";
  }
}

function showPosition(position){
	$("#lat").val(position.coords.latitude);
	$("#lon").val(position.coords.longitude);
  x.innerHTML="MyLatitude: " + position.coords.latitude +
  "<br>MyLongitude: " + position.coords.longitude;
  }

getLocation();