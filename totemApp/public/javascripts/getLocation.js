
var x=document.getElementById("demo");

function getLocation(){
  if (navigator.geolocation){
    navigator.geolocation.getCurrentPosition(showPosition);
  }else{
    x.innerHTML="Geolocation is not supported by this browser.";
  }
}

function showPosition(position){
  x.innerHTML="MyLatitude: " + position.coords.latitude +
  "<br>MyLongitude: " + position.coords.longitude;
  }
  showPosition(getLocation());
