$(document).ready(function() {
	var mapContainer = document.getElementById('map');
		var mapOptions = {
			center: new kakao.maps.LatLng(37.55507, 126.97070),
			level: 8,
			scrollwheel : false,
			draggable : false,
			disableDoubleClickZoom : false,
			disableDoubleClick : false
		};
		var map = new kakao.maps.Map(mapContainer, mapOptions);
		
		

			var geocoder = new kakao.maps.services.Geocoder();
			
			geocoder.addressSearch('서울', function(result, status) {
	
			     if (status === kakao.maps.services.Status.OK) {
	
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
	
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;"></div>'
			        });
			        infowindow.open(map, marker);
			     }
		     });
	
});