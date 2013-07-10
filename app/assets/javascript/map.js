

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
          var name = $(this).find("#name").attr('value');
          var msg = $(this).find("#msg").attr('value');
          var myLatlng = new google.maps.LatLng(lat,lon);

          var infowindow = new google.maps.InfoWindow({
            content: "<strong>" +  name + "</strong> : " + msg
          });

          var marker = new google.maps.Marker({
              position: myLatlng,
              map: map,
              title: 'Hello World!'
          });

          google.maps.event.addListener(marker, 'click', function() {
            infowindow.open(map,marker);
          });

        })  
});