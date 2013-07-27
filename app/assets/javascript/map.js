

$(document).ready(function() {

          var mapOptions = {
            center: new google.maps.LatLng(43,1),
            zoom: 2,
            mapTypeId: google.maps.MapTypeId.ROADMAP
          };

        var map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);


        $(".coord").each(function(){
          var lat=$(this).attr("data-lat");
          var lon=$(this).attr("data-lon");
          var name=$(this).attr("data-name");
          var msg=$(this).attr("data-msg");
          var time=$(this).attr("data-timestamp");
          var myLatlng = new google.maps.LatLng(lat,lon);
          var contentString = '<div id="content">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<h1 id="firstHeading" class="firstHeading">'+name+'</h1>'+
                '<div id="bodyContent">'+
                '<img src="https://d3jpl91pxevbkh.cloudfront.net/cloudinarywimha/image/upload/h_100/v1374678315/5e7e84f2-6c17-468d-86a0-f93a8998a607.jpg">'+
                '<p><b>'+msg+'</b></p>'+
                '<a href="https://twitter.com/share" class="twitter-share-button" data-via="wimha" data-lang="fr" data-text="'+msg+'" data-hashtags="TotemBurningman">Tweeter</a>'+
                '</div>'
                '</div>';
          var infowindow = new google.maps.InfoWindow({
             content: contentString
            /*content: "<strong>" +  name + "</strong> : " + msg*/
          });
          var marker = new google.maps.Marker({
              position: myLatlng,
              map: map,
              title: 'Hello World!'
          });

          google.maps.event.addListener(marker, 'click', function() {
            infowindow.open(map,marker);
            setTimeout(function () { infowindow.close(); }, 5000);
          });

          Ancretime= '#'+ time.substring(1);
          if (window.location.hash == Ancretime) {
            infowindow.open(map,marker);
            map.setZoom(5);
            map.panTo(marker.position);
          }

        })

});

