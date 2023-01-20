<template>
    <div>
      <div style="background-color:rgba(243, 223, 227, 0.2); display: flex;
    justify-content: center;
    align-items: center;; padding: 10%;">
        <h3>찾으시는 매물의 보증금이
        {{answer1}}
        만원정도 인가요?</h3>
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
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const recommendStore = "recommendStore";

export default {
  name : "QuestionItem1",
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
    ...mapState(recommendStore, ["answer1", "low", "high"]),
  },
  methods : {
    ...mapActions(recommendStore, ["choiceDeal", "choiceRent", "choiceLocation"]),
    lowAmount(){
      this.info.high = this.high;
      this.info.low = this.low;
      this.info.flag = "down";
      this.info.now = this.answer1;
      this.choiceDeal(this.info);
    },
    highAmount(){
      this.info.high = this.high;
      this.info.low = this.low;
      this.info.flag = "up";
      this.info.now = this.answer1;
      this.choiceDeal(this.info);
    },
    // 해당 정보가 현재 진행중인데 적절을 선택했다면 다음으로 넘겨줘야 함, 이 때 값은 다 0처리
    next(){
      this.info.now = 0;
      this.info.high = 0;
      this.info.low = 0;
      this.info.flag = "start";
      
      this.choiceRent(this.info);
      this.$router.push({ name : "question2"});
      // this.choiceLocation(this.info);
      // this.$router.push({ name : "question3"});
    }
  }
}
</script>

<style>

</style>