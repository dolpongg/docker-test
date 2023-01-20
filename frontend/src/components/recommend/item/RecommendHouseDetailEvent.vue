<template>
  <div>
    <b-table hover :items="head"></b-table>
    <br><br>
    <div>
      <b-table hover :items="items"></b-table>
    </div>
  </div> 
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
    name : "RecommendHouseDetailEvent",
    computed: {
    ...mapState(houseStore, ["house"]),
    },
    data() {
      return {
        items: [
          {  등급: '0등급', 주변상가: '100미만', _rowVariant: ''},
          {  등급: '1등급', 주변상가: '100이상 150미만', _rowVariant: ''},
          {  등급: '2등급', 주변상가: '150이상 200미만', _rowVariant: ''},
          {  등급: '3등급', 주변상가: '200이상 250미만', _rowVariant: ''},
          {  등급: '4등급', 주변상가: '250이상 300미만', _rowVariant: ''},
          {  등급: '5등급', 주변상가: '300이상', _rowVariant: ''},
        ],
        head: [
        ],
      }
    },
    created() {
      if(this.house.event.total_count < 100) {
        this.head.push({"주변 상가 개수" : this.house.event.total_count, "이벤트 등급" : "0등급"});
        this.items[0]._rowVariant = 'warning';
      }
      else if(this.house.event.total_count < 150) {
        this.head.push({"주변 상가 개수" : this.house.event.total_count, "이벤트 등급" : "1등급"});
        this.items[1]._rowVariant = 'warning';
      }
      else if(this.house.event.total_count < 200) {
        this.head.push({"주변 상가 개수" : this.house.event.total_count, "이벤트 등급" : "2등급"});
        this.items[2]._rowVariant = 'warning';
      }
      else if(this.house.event.total_count < 250) {
        this.head.push({"주변 상가 개수" : this.house.event.total_count, "이벤트 등급" : "3등급"});
        this.items[3]._rowVariant = 'warning';
      }
      else if(this.house.event.total_count < 300) {
        this.head.push({"주변 상가 개수" : this.house.event.total_count, "이벤트 등급" : "4등급"});
        this.items[4]._rowVariant = 'warning';
      }
      else {
        this.head.push({"주변 상가 개수" : this.house.event.total_count, "이벤트 등급" : "5등급"});
        this.items[5]._rowVariant = 'warning';
      }
    }
}
</script>

<style>

</style>