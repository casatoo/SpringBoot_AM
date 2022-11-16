<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN" />
<%@ include file="../common/head.jspf"%>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3fb5c1357bd292db229ec2c6c88c9535"></script>
<script>
	const API_KEY = 'UDknKWMcEt2j1IlOazmpJzieEdhjjaSOMbGQwmF0nEXiBUE2QKGJosC8yLqyllGhvAVgbU3JrtQDaWwPQYfE4w%3D%3D';

	async function getData() {
		const url = 'http://apis.data.go.kr/B552061/schoolzoneChild/getRestSchoolzoneChild?ServiceKey='+ API_KEY + '&searchYearCd=2017&siDo=11&guGun=680&type=json&numOfRows=10&pageNo=1';
		const response = await fetch(url);
		const data = await response.json();
		console.log(data.items.item[0].la_crd);
		console.log(data.items.item[0].lo_crd);

		var container = document.getElementById('map');
		var options = {
			center : new kakao.maps.LatLng(data.items.item[0].la_crd,
					data.items.item[0].lo_crd),
			level : 3
		};

		// 마커가 표시될 위치입니다 
		var markerPosition = new kakao.maps.LatLng(data.items.item[0].la_crd,
				data.items.item[0].lo_crd);

		var map = new kakao.maps.Map(container, options);
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position : markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
	}
	getData();
	
</script>
<div id="map" style="width: 500px; height: 400px;"></div>
<%@ include file="../common/foot.jspf"%>