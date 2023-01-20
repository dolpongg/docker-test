<template>
  <div>
    <h4 style="text-align:center">{{house.type}}</h4>
    <v-row>
      <v-col cols="6">
        <bar-chart1></bar-chart1>
      </v-col>
      <v-col cols="6">
        <bar-chart2></bar-chart2>
      </v-col>
    </v-row>
    <div>
      <b-table striped hover :items="items" style="text-align:center"></b-table>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import BarChart1 from "@/components/house/item/DetailBar1.vue"
import BarChart2 from "@/components/house/item/DetailBar2.vue"
const houseStore = "houseStore";

export default {
    name : "HouseDetailmain",
    computed: {
    ...mapState(houseStore, ["house"]),
    },
    components : {
      BarChart1,
      BarChart2,
    },
    data() {
      return {
        items: [{ 보증금: "", 월세: "", 층수: "", 평수: "", 대출가능: "" }],
      }
    },
    created() {
      this.items[0].보증금 = this.house.dealAmount;
      this.items[0].월세 = this.house.rentMoney;
      this.items[0].층수 = this.house.floor;
      this.items[0].평수 = this.house.area;
      if(this.house.loan) this.items[0].대출가능 = "가능";
      else this.items[0].대출가능 = "불가능";
    },
}
</script>

<style>

</style>