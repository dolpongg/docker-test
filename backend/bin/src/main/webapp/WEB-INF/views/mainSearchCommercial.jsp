<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <%@ include file="/WEB-INF/include/head.jsp" %>
  
  <c:if test="${empty houseinfo && empty lat}">
	  <section id="hero">
	  	<div id="j-location-select" class="main-page">
	      <div class="form-group col-md-2">
	        <select id="sido" class="form-select" aria-label="Default select example">
	          <option selected value="all">시도 선택</option>
	        </select>
	      </div>
	      <div class="form-group col-md-2">
	        <select id="gugun" class="form-select" aria-label="Default select example">
	         <option value="all">구군 선택</option>
	        </select>
	      </div>
	      <div class="form-group col-md-2">
	        <select id="dong" class="form-select" aria-label="Default select example">
	          <option value="all">동 선택</option>
	        </select>
	      </div>
	      <div class="form-group col-md-2">
	        <button type="submit" id="list-btn" class="book-a-table-btn scrollto" style="border: none;">검색</button>
	      </div>
	    </div>
	  </section>
	  <%@ include file="/WEB-INF/include/footer.jsp" %>
	  
	  <script>
		//API 불러오기
  		document.querySelector("#list-btn").addEventListener("click", function () {
      	//localStorage에 저장하고 페이지 이동
     	 //1. localStorage에 저장하기
      	let loca = {};
      	let sidoEl = document.querySelector("#sido");
      	let sidoV = sidoEl[sidoEl.selectedIndex].text;
      	let gugunEl = document.querySelector("#gugun");
    	let gugunV = gugunEl[gugunEl.selectedIndex].text;
    	let dongEl = document.querySelector("#dong");
    	let dongV = dongEl[dongEl.selectedIndex].text;
    	
    	let gugunSel = document.querySelector("#gugun");//구군 저장
    	let dong = document.querySelector("#dong");
    	let dongCode = document.querySelector("#dong").value;
    	
    	console.log(dongCode);
      
      //2,. 페이지 이동
      location.href = "${root}/restaurant/search?sido=" + sidoV + "&gugun=" + gugunV +"&dong=" + dongV ;

      });
  
  </script>
	  
	  
  </c:if>
  <c:if test="${!empty lat}">  	
  	<section id="hero">
  		<div class="container" style="position: absolute; top: 28%; right: 0; left: 0">
        <div class="row">
          <div class="main col">
  			<div>${sido} ${gugun} ${dong} 맛집</div>
  			<div id="map" style="width:100%;height:600px;">

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b289b5247a7e83b4a1d1203a9a7503f1&libraries=services"></script>
	<script>
		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
		var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places(); 
		
		// 키워드로 장소를 검색합니다
		
		ps.keywordSearch("${sido} ${gugun} ${dong} 맛집", placesSearchCB); 
		
		// 키워드 검색 완료 시 호출되는 콜백함수 입니다
		function placesSearchCB (data, status, pagination) {
			var distance = [];
		    if (status === kakao.maps.services.Status.OK) {
		
		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		        // LatLngBounds 객체에 좌표를 추가합니다
		        var bounds = new kakao.maps.LatLngBounds();
		        var lat = "${lat}";
		        var lng = "${lng}";
		        for (var i=0; i<data.length; i++) {
		            displayMarker(data[i]);    
		            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
		            //distance[i] = Math.abs(data[i].y - lat) + Math.abs(data[i].x - lng);
		            data[i].distance = setDistance(data[i].y,data[i].x,${lat},${lng});
		        }
		        
		        
		        //distance.sort();
		       // console.log(distance);
		        console.log(data);
		        
		        //퀵정렬!!
		        quickSort(data);
		        console.log(data);
		        
		        
		        for(var i=0; i<data.length; i++){
		        	//총거리, 도보, 자전거 시간을 구하고 html에 출력
		        	
		        	getTimeHtml(data[i].distance, data[i].place_name);
		        	
		        }
		        

		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		        map.setBounds(bounds);
		    }
		}
		
		
		
		// 지도에 마커를 표시하는 함수입니다
		function displayMarker(place) {
		    
		    // 마커를 생성하고 지도에 표시합니다
		    var marker = new kakao.maps.Marker({
		        map: map,
		        position: new kakao.maps.LatLng(place.y, place.x) 
		    });
		
		    // 마커에 클릭이벤트를 등록합니다
		    kakao.maps.event.addListener(marker, 'click', function() {
		        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
		        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
		        infowindow.open(map, marker);
		        var level = map.getLevel();   
	        	  // 지도를 1레벨 올립니다 (지도가 축소됩니다)
	        	  map.setLevel(level - 3);
		    });
		}
		function setDistance(lat1, lng1, lat2, lng2){
			const R = 6371e3; // 지구의 반지름 (meter)

			// 좌표를 라디안 단위로 변환
			const r1 = lat1 * Math.PI / 180;
			const c1 = lat2 * Math.PI / 180;
			const r2 = (lat2 - lat1) * Math.PI / 180;
			const c2 = (lng2 - lng1) * Math.PI / 180;
			
			const a = Math.sin(r2 / 2) * Math.sin(r2 / 2) +
					  Math.cos(r1) * Math.cos(c1) *
			          Math.sin(c2 / 2) * Math.sin(c2 / 2);
			const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

			const distance = R * c;
			return distance
		}
		
		function getTimeHtml(distance, name) { //출발지1, 도착지2
			
			

		    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
		    var walkkTime = distance / 67 | 0;
		    var walkHour = '', walkMin = '';

		    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
		    if (walkkTime > 60) {
		        walkHour = Math.floor(walkkTime / 60) +'시간' ;
		    }
		    walkMin = walkkTime % 60 +'분';

		    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
		    var bycicleTime = distance / 227 | 0;
		    var bycicleHour = '', bycicleMin = '';

		    // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
		    if (bycicleTime > 60) {
		        bycicleHour = Math.floor(bycicleTime / 60) + '시간';
		    }
		    bycicleMin = bycicleTime % 60 + '분';

		    // 거리와 도보 시간, 자전거 시간로 만들어 리턴합니다
		    var tbody = document.getElementById('tableList');
		    
		    const tr = document.createElement("tr");
            tr.innerHTML = '<td>'+name+'</td><td>'+distance+'m</td><td>'+walkHour + walkMin+'</td><td>'+bycicleHour + bycicleMin+'</td>';
		    tbody.appendChild(tr);
		    
		    console.log(distance);
		}
		
		function quickSort(data, left = 0, right = data.length - 1) {
			var lat = ${lat};
			var lng = ${lng};
			
			  if (left >= right) {
			    return;
			  }
			  const mid = Math.floor((left + right) / 2);
			  const pivot = data[mid].distance;
			  const partition = divide(data, left, right, pivot);
			  
			  quickSort(data, left, partition - 1);
			  quickSort(data, partition, right);

			  function divide(data, left, right, pivot) {
			    while (left <= right) {
			      while (data[left].distance < pivot) {
			        left++;
			      }
			      while (data[right].distance > pivot) {
			        right--;
			      }

			      //바꾸기
			      if (left <= right) {
			        let temp = data[left];
			        data[left] = data[right];
			        data[right] = temp;
			        left++;
			        right--;
			      }
			    }
			    return left;
			  }
			  
			  return data;
			}
			
	</script>
	</div>
	
	</div>
		<div class="main col" style="height: 650px; overflow: auto">
            <table class="table table-hover text-center">
              <tr>
                <th>맛집 이름</th>
                <th>총거리</th>
                <th>도보</th>
                <th>자전거</th>
              </tr>
              <tbody id="tableList">
              
              </tbody>
              
            </table>
            
          </div>
	</div>
	</div>
  	</section>
  </c:if>
  
  <c:if test="${!empty houseinfo}">
  	<section id="hero">
  		<div class="container" style="position: absolute; top: 28%; right: 0; left: 0">
        <div class="row">
          <div class="main col">
		<div class="main col" style="height: 650px; top:350px; width:50%; overflow: auto">
            <table class="table table-hover text-center">
              <tr>
                <th>아파트이름</th>
              </tr>
              <tbody id="aptlist">
              <c:forEach var="house" items="${houseinfo}">
              <tr>
              	<td>
              		<a id="aptName" onclick="" href= ${root}/restaurant/shortDistance?lat=${house.lat}&&lng=${house.lng}&sido=${sido}&gugun=${gugun}&dong=${dong} >${house.APTName}</a>
              	</td>
              </tr>
              </c:forEach>
              </tbody>
              
            </table>
          </div>
		</div>
	</div>
	</div>
  	</section>
  </c:if>

</body>
</html>