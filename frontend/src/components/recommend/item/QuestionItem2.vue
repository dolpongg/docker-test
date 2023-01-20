<template>
  <div>
    <div style="background-color:rgba(243, 223, 227, 0.2); display: flex;
    justify-content: center;
    align-items: center;; padding: 10%;">
      <h3>찾으시는 매물의 월세가
      {{answer2}}
      (+- 5만원)만원 인가요?</h3>
    </div>
    <v-container>
        <v-row>
          <v-col class="col-4">
            <v-btn @click="lowAmount()" style="padding: 0 150px;" >미만</v-btn>
          </v-col>
         <v-col class="col-4">
            <v-btn  @click="next()" style="padding: 0 150px; color:rgb(244,243,238);" color="rgba(17,45,78,0.85)">적정</v-btn>
          </v-col>
          <v-col class="col-4">
            <v-btn variant="danger" @click="highAmount()" style="padding: 0 150px;">이상</v-btn>
          </v-col>
        </v-row>
          
        </v-container>
    <!-- <div style="margin:5% 0 0 40%">
      <b-button variant="primary" @click="lowAmount()" style="margin-right:10px;">미만</b-button>
      <b-button variant="warning" @click="next()" style="margin-right:10px">적정</b-button>
      <b-button variant="danger" @click="highAmount()">이상</b-button>
    </div> -->
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const recommendStore = "recommendStore";

export default {
  name : "QuestionItem2",
  data() {
    return {
      info : {
        now: "",
        low: "",
        high: "",
        flag: "",
      },
    }
  },
  computed: {
    ...mapState(recommendStore, ["answer2", "low", "high"]),
  },
  methods : {
    ...mapActions(recommendStore, ["choiceRent", "choiceLocation"]),
    lowAmount(){
      this.info.high = this.high;
      this.info.low = this.low;
      this.info.flag = "down";
      this.info.now = this.answer2;
      this.choiceRent(this.info);
    },
    highAmount(){
      this.info.high = this.high;
      this.info.low = this.low;
      this.info.flag = "up";
      this.info.now = this.answer2;
      this.choiceRent(this.info);
    },
    // 해당 정보가 현재 진행중인데 적절을 선택했다면 다음으로 넘겨줘야 함, 이 때 값은 다 0처리
    next(){
      this.info.now = 0;
      this.info.high = 0;
      this.info.low = 0;
      this.info.flag = "start";
      
      this.choiceLocation(this.info);
      this.$router.push({ name : "question3"});
    }
  }
}
</script>

<style>

</style>