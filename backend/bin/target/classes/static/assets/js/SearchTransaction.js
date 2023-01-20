var loca = JSON.parse(localStorage.getItem("loca"));

let url =
"http://localhost:8080/main";
let queryParams =
        encodeURIComponent("serviceKey") + "=" + "%2F6xaESXKOaldVYf%2FMsiLgc26XYuDXapoTozpFOPQtdquWk9EkOMm%2BuYFFjhBlf3nZL8MVK9cUWymPA5wgAscaQ%3D%3D"; /*Service Key*/
    queryParams +=
        "&" +
        encodeURIComponent("LAWD_CD") +
        "=" +
        encodeURIComponent(loca.regCode); /*아파트소재 구군*/
    queryParams +=
        "&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(loca.dealYM); /*조회년월*/
    queryParams +=
        "&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1"); /*페이지번호*/
    queryParams +=
        "&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent("30"); /*페이지당건수*/

fetch(`${url}?${queryParams}`)
.then((response) => response.text())
.then((data) => makeList(data));

function makeList(data) {
    document.querySelector("table").setAttribute("style", "display: ;");
    let tbody = document.querySelector("#aptlist");
    let parser = new DOMParser();
    const xml = parser.parseFromString(data, "application/xml");
    // console.log(xml);
    let apts = xml.querySelectorAll("item");
    // console.log(xml)


    /////---------------지도에 마커 표시-------------///////
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 7 // 지도의 확대 레벨
        };  
        // console.log(mapContainer)
        // 지도를 생성합니다    
        var map = new kakao.maps.Map(mapContainer, mapOption); 
        apts.forEach((apt) => {
        // 주소-좌표 변환 객체를 생성합니다
            var geocoder = new kakao.maps.services.Geocoder();

            // 주소변수!!!!
            let dongV = apt.querySelector("법정동").innerHTML;
            let jibunV = apt.querySelector("지번").innerHTML;
            let loca = JSON.parse(window.localStorage.getItem("loca"));
            let address = `${loca.sido} ${loca.gugun}${dongV} ${jibunV}`;
            console.log()
            console.log(address);
            // console.log(dongV);

            // 주소로 좌표를 검색합니다
            geocoder.addressSearch(address, function(result, status) {
            // console.log(1)
            // 정상적으로 검색이 완료됐으면 
                if (status === kakao.maps.services.Status.OK) {
                    // console.log(111)
                    // console.log(address)
                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                    // 결과값으로 받은 위치를 마커로 표시합니다
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });

                    marker.setMap(map);

                    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    map.setCenter(coords);


                    // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
                    var iwContent = '<div style="padding:5px; cursor:pointer();" onclick = zoomIn()>' +apt.querySelector("아파트").innerHTML +'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
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
                    });
                } 
            });
    });
    }



function zoomIn() {    
    // 현재 지도의 레벨을 얻어옵니다
    var level = map.getLevel(); 
    
    // 지도를 1레벨 올립니다 (지도가 축소됩니다)
    map.setLevel(level + 1);
    
    // 지도 레벨을 표시합니다
    displayLevel(); 
}    
