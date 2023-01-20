<template>
  <div>
    <div>
      <h6 v-show="userInfo.type=='직장인'">설정한 회사과 {{house.distance}}m 떨어져 있습니다!</h6>
      <h6 v-show="userInfo.type=='대학생'">설정한 학교과 {{house.distance}}m 떨어져 있습니다!</h6>
      <br><br>
      이 집의 주변 지하철 역 정보
      <br>
      <b-table hover :items="items"></b-table>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
    name : "RecommendHouseDetailDis",
    computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(memberStore, ["userInfo"]),
    },
    data() {
      return {
        sub: [],
        items: [],
      }
    },
    created() {
      this.sub = this.house.nearSubways.split(',');
      this.sub.forEach(s => {
        var s1 = s.split('역');
        this.items.push({ "지하철 역" : s1[0]+"역", "호선" : s1[1]});
      });
      
    },
}
</script>

<style>

</style>