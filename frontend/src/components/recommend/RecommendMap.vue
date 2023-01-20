<template>
    <div>
        <div id="map"></div>
    </div>
</template>

<script>
import { mapState} from "vuex";
const houseStore = "houseStore";

export default {
name: "RecommendMap",
data() {
    return {
    markers: [],
    infowindow: null,
    };
},
computed: {
    ...mapState(houseStore, ["houseMakers"]),
},
watch : {
    houseMakers : function (houseMakers) {
    //     if (window.kakao && window.kakao.maps) {
    // this.initMap();
    // } else {
    // const script = document.createElement("script");
    // /* global kakao */
    // script.onload = () => kakao.maps.load(this.initMap);
    // script.src =
    //     "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=57dd21f3fc158412d95faecd7bab8cf2";
    // document.head.appendChild(script);
    // }

    
        if (this.markers.length > 0) {
            this.markers.forEach((marker) => marker.setMap(null));
        }

        const positions = houseMakers.map(
            (position) => new kakao.maps.LatLng(...position)
        );


        if (positions.length > 0) {
            this.markers = positions.map(
            (position) =>
                new kakao.maps.Marker({
                map: this.map,
                position,
                })
            );

            const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
            );

            this.map.setBounds(bounds);
        }
    },
    // displayInfoWindow() {
    // if (this.infowindow && this.infowindow.getMap()) {
    //     //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
    //     this.map.setCenter(this.infowindow.getPosition());
    //     return;
    // }

    // var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
    //     iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // this.infowindow = new kakao.maps.InfoWindow({
    //     map: this.map, // 인포윈도우가 표시될 지도
    //     position: iwPosition,
    //     content: iwContent,
    //     removable: iwRemoveable,
    // });

    // this.map.setCenter(iwPosition);
    // },
},
mounted() {
    if (window.kakao && window.kakao.maps) {
    this.initMap();
    } else {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.initMap);
    script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=57dd21f3fc158412d95faecd7bab8cf2";
    document.head.appendChild(script);
    }
    console.log(this.houseMakers);
    const positions = this.houseMakers.map(
            (position) => new kakao.maps.LatLng(...position)
        );


        if (positions.length > 0) {
            this.markers = positions.map(
            (position) =>
                new kakao.maps.Marker({
                map: this.map,
                position,
                })
            );

            const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
            );

            this.map.setBounds(bounds);
        }
},
methods: {
    initMap() {
    const container = document.getElementById("map");
    const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
    };

    //지도 객체를 등록합니다.
    //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
    this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
    const container = document.getElementById("map");
    container.style.width = `${size}px`;
    container.style.height = `${size}px`;
    this.map.relayout();
    },
    
},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
width: 820px;
height: 820px;
}

.button-group {
margin: 10px 0px;
}

button {
margin: 0 3px;
}
</style>
