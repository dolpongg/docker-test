<template>
  <div>
    <br><br>
    <v-app id="inspire">
      <v-data-table
        :headers="headers"
        :items="house.health"
        multi-sort
        class="elevation-1"
        @click:row="showDetail"
      ></v-data-table>
    </v-app>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
    name : "HouseDetailHealth",
    computed: {
    ...mapState(houseStore, ["house"]),
    },
    data () {
    return {
      isHealth : false,
      headers: [
        {
          text: '헬스장 이름',
          align: 'start',
          sortable: false,
          value: 'place_name',
        },
        { text: '거리', value: 'distance' },
        { text: '위치', value: 'road_address_name' },
        { text: '번호', value: 'phone' },
      ],
    }
  },
  created() {
    this.house.health.forEach( h => {
      console.log("health", h);
      if(h.distance <= 500) this.isHealth = true;
    });
  },
  methods: {
    showDetail(row){
      window.open(row.place_url);
    },
  },
}
</script>

<style>

</style>