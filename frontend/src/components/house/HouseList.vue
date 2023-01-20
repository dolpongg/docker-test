<template>
  <div id="app">
    <v-app id="inspire">
      <v-data-table
        :headers="headers"
        :items="houses"
        multi-sort
        class="elevation-1"
        @click:row="showDetail"
      ></v-data-table>
    </v-app>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  name: "HouseList",
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  data () {
    return {
      headers: [
        {
          text: '집 종류',
          align: 'start',
          sortable: false,
          value: 'type',
        },
        { text: '월세', value: 'rentMoney' },
        { text: '보증금', value: 'dealAmount' },
      ],
    }
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList", "detailHouse"]),
    async showDetail(row){
      console.log("ok", row);
      let info = {
        sido: row.sidoName,
        gugun: row.gugunName,
        dong: row.dongName,
        aptcode: row.aptcode,
        userid: this.userInfo.id,
      }
      await this.detailHouse(info);
      this.$router.push({ name : "houseDetailMain"});
    },
  },
};
</script>

<style>
#app{
  width: 100%;
  max-width: 100%;
}
  #inspire{
    background-color: rgb(244,243,238);
  }
</style>
