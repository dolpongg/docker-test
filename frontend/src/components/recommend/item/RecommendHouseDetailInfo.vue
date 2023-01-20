<template>
  <div>
    <h4>자취방 소개</h4>
    <h5>{{house.title}}</h5>
    {{house.description}}
    <br>
    <b-button @click="goInfo()">보러가기</b-button>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name : "RecommendHouseDatailInfo",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  methods : {
    goInfo(){
      window.open(this.house.url);
    }
  }
}
</script>

<style>

</style>