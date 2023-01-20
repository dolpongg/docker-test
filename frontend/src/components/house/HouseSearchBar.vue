<template>
  <b-row class="mt-4 mb-4 text-center" style="background-color:rgba(229,166,113,0.7); border-radius: 15px;">
    <b-col cols="3">
      <div style="margin:auto; padding-top: 3%;"> 매물 지역 선택</div>
    </b-col>
    <b-col cols="3">
      <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
    </b-col>
    <b-col cols="3">
      <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
    </b-col>
    <b-col cols="3">
      <b-form-select v-model="dongCode" :options="dongs" @change="searchApt"></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList"]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST", "CLEAR_APT_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      // console.log(this.sidoCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      let sidoAndGugun = { "sido" : this.sidoCode , "gugun" : this.gugunCode};
      if (this.sidoCode && this.gugunCode) this.getDong(sidoAndGugun);
    },
    async searchApt() {
      let locationInfo = {
        sido: this.sidoCode,
        gugun: this.gugunCode,
        dong: this.dongCode
      }
      if (this.gugunCode) {
        await this.getHouseList(locationInfo);
      }
      console.log(this.houses);
      this.$router.push({ name : "houseList" });
    },
  },
};
</script>

<style></style>
