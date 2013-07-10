

$(document).ready(function() {

          var mapOptions = {
            center: new google.maps.LatLng(43,1),
            zoom: 2,
            mapTypeId: google.maps.MapTypeId.ROADMAP
          };

        var map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);


        $(".coord").each(function(){
          var lat=$(this).find("#lat").attr('value');
          var lon = $(this).find("#lon").attr('value');
          var myLatlng = new google.maps.LatLng(lat,lon);

          var marker = new google.maps.Marker({
              position: myLatlng,
              map: map,
              title: 'Hello World!'
          });
        })  
});