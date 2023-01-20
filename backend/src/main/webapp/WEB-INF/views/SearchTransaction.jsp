<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
  <%@ include file="/WEB-INF/include/head.jsp" %>

    <!-- ======= Hero Section ======= -->
    <section id="hero">
      <div id="j-location-select" style="top: 180px" class="main-page">
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
            <option value="all">동 선택</option>
          </select>
        </div>
        <div class="form-group col-md-2">
          <select id="year" class="form-select" aria-label="Default select example">
            <option value="all">연도</option>
          </select>
        </div>
        <div class="form-group col-md-2">
          <select id="month" class="form-select" aria-label="Default select example">
            <option value="all">월</option>
          </select>
        </div>
        <div class="form-group col-md-2">
          <button id="list-btn" class="book-a-table-btn scrollto" style="border: none">조회</button>
        </div>
      </div>

      <div class="container" style="position: absolute; top: 28%; right: 0; left: 0">
        <div class="row">
          <div class="main col">
            <div id="map" style="width:100%;height:600px;">
						<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b289b5247a7e83b4a1d1203a9a7503f1"></script>
						<script>
						
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
						    mapOption = { 
						        center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표
						        level: 3 // 지도의 확대 레벨
						    };
						
						var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
						map.relayout();
						<c:forEach var="house" items="${houses}">
					          var coords = new kakao.maps.LatLng(${house.lat}, ${house.lng});
					          
					          // 결과값으로 받은 위치를 마커로 표시합니다
					          var marker = new kakao.maps.Marker({
					        	  "map" : map,
					              "position" : coords
					          });
					          console.log(coords);
					          console.log(marker);
					          
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
					        	  /*
					        	  let url="${root}/main/";
					        	  fetch(url)
					        	  .then(response = > response.json())
					        	  .then(data => {
					        		  console.log(data);
					        		  
					        	  })
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
                <th>아파트이름</th>
                <th>층</th>
                <th>면적</th>
                <th>법정동</th>
                <th>거래금액</th>
              </tr>
              <tbody id="aptlist">
              <c:forEach var="house" items="${houses}">
              <tr>
              	<td>
              		<a href= ${root}/main/detail?sido=${house.sidoName}&gugun=${house.gugunName}&dong=${house.dongName}&year=${house.dealYear}&month=${house.dealMonth}&APTName=${house.APTName}>${house.APTName}</a>
              	</td>
              	<td>
              		${house.floor }
              	</td>
              	<td>
              		${house.area }
              	</td>
              	<td>
              		${house.dongName }
              	</td>
              	<td>
              		${house.dealAmount }
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
      console.log(dongEl);
      let dongV = dongEl[dongEl.selectedIndex].text;
     
      let gugunSel = document.querySelector("#gugun");//구군 저장
      let dong = document.querySelector("#dong");
      let yearSel = document.querySelector("#year");
      let year = yearSel[yearSel.selectedIndex].value;
      let monthSel = document.querySelector("#month");
      let month = monthSel[monthSel.selectedIndex].value;
      let dealYM = year + month;
      
      loca.sido = sidoV;
      loca.gugun = gugunV;
      loca.dong = dongV;
      loca.year = year;
      loca.month = month;
      loca.dealYM = dealYM;

      window.localStorage.setItem("loca",JSON.stringify(loca));


      //2,. 페이지 이동
      location.href = "${root}/main/search?&sido=" + sidoV + "&gugun=" + gugunV +"&dong=" + dongV + "&year=" + year  + "&month=" + month + "&page=0";

      });
  
  </script>
  </body>
</html>
