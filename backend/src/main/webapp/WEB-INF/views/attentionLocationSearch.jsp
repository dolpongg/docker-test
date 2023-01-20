
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/include/head.jsp" %>

<section id="hero">
      <div class="container" style="position: absolute; top: 28%; right: 0; left: 0">
        <div class="row">
          <div class="main col">
            <div id="map"">
						<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b289b5247a7e83b4a1d1203a9a7503f1"></script>
						<script>
						
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
						    mapOption = { 
						        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						        level: 3 // 지도의 확대 레벨
						    };
						
						var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
						<c:forEach var="house" items="${houses}">
				          var coords = new kakao.maps.LatLng(${house.lat}, ${house.lng});
				      	 
				          // 결과값으로 받은 위치를 마커로 표시합니다
				          var marker = new kakao.maps.Marker({
				              map: map,
				              position: coords
				          });
				          
				          marker.setMap(map);
					       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			              map.setCenter(coords);
				       // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
			              var iwContent = '<div style="width:200px; height:100px; padding:5px; cursor:pointer();" >' 
			              			+"${house.APTName}"
			              			+"<br> 거래금액 : ${house.dealAmount} 만원 <br> 거래일 : ${house.dealYear}년 ${house.dealMonth}월"+'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
			              iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

			              // 인포윈도우를 생성합니다
			              var infowindow = new kakao.maps.InfoWindow({
			              content : iwContent,
			              removable : iwRemoveable
			              });

			              // 마커에 클릭이벤트를 등록합니다
			              kakao.maps.event.addListener(marker, 'click', function() {
			              	// 마커 위에 인포윈도우를 표시합니다
				              infowindow.open(map, marker);  
					          
					       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				              map.setCenter(coords);
					       
				              var level = map.getLevel();   
				        	  // 지도를 1레벨 올립니다 (지도가 축소됩니다)
				        	  map.setLevel(level - 3);
					       
			              });
			              
			              
			              
			          function detail() {    
			        	    
			        	    /* 비동기 처리 - url이 안넘어감 왜?!!!!!!!!!!!
			        	    $.ajax({
                                url : "${root}/main?action=detail",
                                type : "post",
                                data : {sidoName:"${house.sidoName}", gugunName:"${house.gugunName}", dongName:"${house.dongName}", dealYear:"${house.dealYear}", dealMonth:"${house.dealMonth}", APTName:"${house.APTName}"},
                                success : function(data) {
                                	console.log(data.detail);
                                 },
                                error : function() {
                                    console.log(error);
                                    console.log("GGG");
                                }
                            });
			        	    */
			        	}   
			          
			          </c:forEach>
			          function initTable() {
			        	    let tbody = document.querySelector("#aptlist");
			        	    let len = tbody.rows.length;
			        	    for (let i = len - 1; i >= 0; i--) {
			        	        tbody.deleteRow(i);
			        	    }
			        	}
			</script>
              
            </div>
          </div>

          <div class="main col" style="height: 650px; overflow: auto">
            <table class="table table-hover text-center">
              <tr>
                <th>시</th>
                <th>구</th>
                <th>동</th>
                <th>매물 보기</th>
                <th>삭제 버튼</th>
              </tr>
              <tbody id="aptlist">
              <c:forEach var="local" items="${locals}">
              <tr>
              	<td>
              		${local.sidoName }
              	</td>
              	<td>
              		${local.gugunName }
              	</td>
              	<td>
              		${local.dongName }
              	</td>
              	<td>
              		<a href="${root}/main/detailLocal?sido=${local.sidoName}&gugun=${local.gugunName}&dong=${local.dongName}">매물보기</a>
              	</td>
              	<td>
              		<a href="${root}/main/deleteLocal?dongCode=${local.dongCode}" >삭제</a>
              	</td>
              </tr>
              
              </c:forEach>
              </tbody>
              
            </table>
            
            <c:if test="${detail ne null}">
             <br>
	            <nav aria-label="Page navigation example">
	           
				  <ul class="pagination">
				    <li class="page-item"><a class="page-link" href="${root}/main/search?sido=${houses.get(0).sidoName}&gugun=${houses.get(0).gugunName}&dong=${houses.get(0).dongName}&year=${houses.get(0).dealYear}&month=${houses.get(0).dealMonth}&page=0">1</a></li>
				    <li class="page-item"><a class="page-link" href="${root}/main/search?sido=${houses.get(0).sidoName}&gugun=${houses.get(0).gugunName}&dong=${houses.get(0).dongName}&year=${houses.get(0).dealYear}&month=${houses.get(0).dealMonth}&page=3">2</a></li>
				    <li class="page-item"><a class="page-link" href="${root}/main/search?sido=${houses.get(0).sidoName}&gugun=${houses.get(0).gugunName}&dong=${houses.get(0).dongName}&year=${houses.get(0).dealYear}&month=${houses.get(0).dealMonth}&page=6">3</a></li>
				  </ul>
				</nav>
			</c:if>
          </div>
          
          
        </div>
      </div>
    </section>
    <!-- End Hero -->

    <!-- ë¡ê·¸ì¸ëª¨ë¬ -->
    <div id="login-form">
      <form name="login-form" class="login-form">
        <input id="id" name="id" type="text" placeholder="ID" />
        <input id="password" name="password" type="password" placeholder="password" />
        <button class="login-btn" onclick="login()" type="button">로그인</button>
        <button onclick="location.href='register.html'" type="button">회원가입</button>
      </form>
    </div>

  <%@ include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>