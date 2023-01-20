<template>
  <b-container v-if="house" class="bv-example-row">
    <v-row>
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="2000"
        controls
        indicators
        background="#ababab"
        img-width="500"
        img-height="300"
        style="text-shadow: 1px 1px 2px #333;"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
      >
        <!-- Slides with custom text -->
        <!-- "require(`@/assets/images/${imgName}.png`)" -->
        <b-carousel-slide v-for="(item, index) in imgUrl" :key="index" :img-src="item" img-width="500" img-height="300">
        </b-carousel-slide>
      </b-carousel>
    </v-row>
    <v-row>
      <v-col cols="6">
        <h4> {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }} {{ house.jibun }}</h4>
      </v-col>
      <v-col cols="6">
        <b-dropdown id="dropdown-1" text="정보보기" class="m-md-2">
          <b-dropdown-item @click="toMain">간단한 정보</b-dropdown-item>
          <b-dropdown-item @click="toDis">목적지 거리</b-dropdown-item>
          <b-dropdown-item @click="toInfo">자취방 소개</b-dropdown-item>
          <b-dropdown-item @click="toEvent">이벤트 등급</b-dropdown-item>
          <b-dropdown-item @click="toHealth">헬세권 확인</b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
          <!-- <b-dropdown-item active>Active action</b-dropdown-item> -->
          <!-- <b-dropdown-item disabled>Disabled action</b-dropdown-item> -->
        </b-dropdown>
      </v-col>
    </v-row>
    <b-row>
      <router-view></router-view>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "RecommendDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  data() {
    return {
      slide: 0,
      sliding: null,
      imgUrl: [],
      num: ""
    }
  },
  created() {
    for(let i = 1; i <= this.house.img; i++){
      let url = "https://ic.zigbang.com/ic/items/" + this.house.aptcode + "/" + i + ".jpg?w=500&h=300"
      this.imgUrl.push(url);
    }
    this.num = this.house.img;
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    onSlideStart(slide) {
      console.log(slide);
      this.sliding = true
    },
    onSlideEnd(slide) {
      console.log(slide);
      this.sliding = false
    },
    toMain(){
      this.$router.push({ name : "recommendHouseDetailMain"});
    },
    toDis(){
      this.$router.push({ name : "recommendHouseDetailDis"});
    },
    toInfo(){
      this.$router.push({ name : "recommendHouseDetailInfo"});
    },
    toEvent(){
      this.$router.push({ name : "recommendHouseDetailEvent"});
    },
    toHealth(){
      this.$router.push({ name : "recommendHouseDetailHealth"});
    },
  },
};
</script>

<style scoped>
  .img-fluid{
    height: 300px;
  }
  .row{
    width: 500px;
  }
</style>
