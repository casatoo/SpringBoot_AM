<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<%@ include file="../common/head.jspf"%>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3fb5c1357bd292db229ec2c6c88c9535"></script>
	<script>
    const API_KEY = 'UDknKWMcEt2j1IlOazmpJzieEdhjjaSOMbGQwmF0nEXiBUE2QKGJosC8yLqyllGhvAVgbU3JrtQDaWwPQYfE4w%3D%3D';

    async function getData(){
        const url ='http://apis.data.go.kr/B552061/schoolzoneChild/getRestSchoolzoneChild?ServiceKey='+API_KEY+'&searchYearCd=2017&siDo=11&guGun=680&type=json&numOfRows=10&pageNo=1';
        const response = await fetch(url);
        const data = await response.json();
        console.log(data.items.item[0].la_crd);
        console.log(data.items.item[0].lo_crd);

	
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(data.items.item[0].la_crd, data.items.item[0].lo_crd),
			level: 3
		};

		// 마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(data.items.item[0].la_crd,data.items.item[0].lo_crd); 

		var map = new kakao.maps.Map(container, options);
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
    }
    getData();
	</script>
		<div id="map" style="width:500px;height:400px;"></div>
<div class="flex justify-center justify-around mt-12 ">
	<div class="max-w-sm rounded overflow-hidden shadow-lg">
		<img class="w-full"
			src="https://images.pexels.com/photos/2387873/pexels-photo-2387873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
			alt="Sunset in the mountains">
		<div class="px-6 py-4">
			<div class="font-bold text-xl mb-2">The Coldest Sunset</div>
			<p class="text-gray-700 text-base">Lorem ipsum dolor sit amet,
				consectetur adipisicing elit. Voluptatibus quia, nulla! Maiores et
				perferendis eaque, exercitationem praesentium nihil.</p>
		</div>
		<div class="px-6 pt-4 pb-2">
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#photography</span>
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#travel</span>
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#winter</span>
		</div>
	</div>
	<div class="max-w-sm rounded overflow-hidden shadow-lg">
		<img class="w-full"
			src="https://images.pexels.com/photos/3183132/pexels-photo-3183132.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
			alt="Sunset in the mountains">
		<div class="px-6 py-4">
			<div class="font-bold text-xl mb-2">The Coldest Sunset</div>
			<p class="text-gray-700 text-base">Lorem ipsum dolor sit amet,
				consectetur adipisicing elit. Voluptatibus quia, nulla! Maiores et
				perferendis eaque, exercitationem praesentium nihil.</p>
		</div>
		<div class="px-6 pt-4 pb-2">
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#photography</span>
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#travel</span>
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#winter</span>
		</div>
	</div>
	<div class="max-w-sm rounded overflow-hidden shadow-lg">
		<img class="w-full"
			src="https://images.pexels.com/photos/1342650/pexels-photo-1342650.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
			alt="Sunset in the mountains">
		<div class="px-6 py-4">
			<div class="font-bold text-xl mb-2">The Coldest Sunset</div>
			<p class="text-gray-700 text-base">Lorem ipsum dolor sit amet,
				consectetur adipisicing elit. Voluptatibus quia, nulla! Maiores et
				perferendis eaque, exercitationem praesentium nihil.</p>
		</div>
		<div class="px-6 pt-4 pb-2">
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#photography</span>
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#travel</span>
			<span
				class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#winter</span>
		</div>
	</div>
</div>
<%@ include file="../common/foot.jspf"%>