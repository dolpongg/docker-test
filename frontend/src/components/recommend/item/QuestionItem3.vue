<template>
  <div>
    <div style="background-color:rgba(243, 223, 227, 0.2); display: flex;
    justify-content: center;
    align-items: center;; padding: 10%;">
      <h3 v-show="userInfo.type=='대학생'">찾으시는 집의 거리가 대학으로부터
      {{answer3}}
      m 안으로 찾으시나요?</h3>
      <h3 v-show="userInfo.type=='직장인'">찾으시는 집의 거리가 회사로부터
      {{answer3}}
      m 안으로 찾으시나요?</h3>
    </div>

    <v-container>
        <v-row>
          <v-col class="col-6">
            <v-btn @click="highAmount()" style="padding: 0 250px;" >이상</v-btn>
          </v-col>
         <v-col class="col-6">
            <v-btn  @click="next()" style="padding: 0 250px; color:rgb(244,243,238);" color="rgba(17,45,78,0.85)">적정</v-btn>
          </v-col>
        </v-row>
          
        </v-container>
    <!-- <div style="margin:5% 0 0 40%">
      <b-button variant="warning" @click="next()" style="margin-right:10px">적정</b-button>
      <b-button variant="danger" @click="highAmount()">이상</b-button>
    </div> -->
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const recommendStore = "recommendStore";
const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  name : "QuestionItem3",
  data() {
    return {
      info : {
        now: "",
        high: "",
        flag: "",
      },
    }
  },
  computed: {
    ...mapState(recommendStore, ["answer1", "answer2", "answer3", "low", "high", "recommendList"]),
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(houseStore, ["houses", "houseMakers"]),
  },
  methods : {
    ...mapActions(recommendStore, ["choiceLocation", "lastchoice"]),
    ...mapMutations(houseStore, ["CLEAR_HOUSE_MAKERS", "CLEAR_APT_LIST", "SET_HOUSE_LIST", "SET_HOUSE_MAKERS"]),
    highAmount(){
      this.info.high = this.high;
      this.info.flag = "up";
      this.info.now = this.answer3;
      this.choiceLocation(this.info);
    },
    // 해당 정보가 현재 진행중인데 적절을 선택했다면 다음으로 넘겨줘야 함, 이 때 값은 다 0처리
    async next(){
      let aInfo = {
        dealAmount: this.answer1,
        rentMoney: this.answer2,
        distance: this.answer3,
        userid: this.userInfo.id,
      }
      
      await this.lastchoice(aInfo);
      if(this.recommendList.length > 0){
        alert("적당한 방이" + this.recommendList.length + "개 있습니다.");
        this.CLEAR_HOUSE_MAKERS();
        this.CLEAR_APT_LIST();
        this.SET_HOUSE_LIST(this.recommendList);
        console.log("houses", this.houses);
        this.SET_HOUSE_MAKERS(this.recommendList);
        console.log("maker", this.houseMakers);
        this.$router.push({ name : "recommendList"});
      }
      else{
        alert("적당한 자취방이 없습니다.");
        this.$router.push({ name : "index"});
      }
      
    }
  }
}
</script>

<style>

</style>