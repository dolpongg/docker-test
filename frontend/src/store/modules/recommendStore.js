import { amountDeal, amountRent, location, finish } from "@/api/recommend";

const recommendStore = {
  namespaced: true,
  state: {
    answer1 : "0",
    answer2 : "0",
    answer3 : "0",
    low: "0",
    high: "0",
    reommendList: []
  },
  getters: {
    checkAnswer1: function (state){
      return state.answer1;
    },
    checkAnswer2: function (state){
      return state.answer2;
    },
    checkAnswer3: function (state){
      return state.answer3;
    },
    checkAnswer4: function (state){
      return state.answer4;
    },
  },
  mutations: {
    SET_ANSWER1: (state, nowAnswer) => {
      state.answer1 = nowAnswer;
    },
    SET_ANSWER2: (state, nowAnswer) => {
      state.answer2 = nowAnswer;
    },
    SET_ANSWER3: (state, nowAnswer) => {
      state.answer3 = nowAnswer;
    },
    SET_ANSWER4: (state, nowAnswer) => {
      state.answer4 = nowAnswer;
    },
    SET_LOW: (state, nowLow) => {
      state.low = nowLow;
    },
    SET_HIGH: (state, nowHigh) => {
      state.high = nowHigh;
    },
    CLEAR_RECOMMEND_LIST: (state) => {
      state.recommendList = [];
    },
    SET_RECOMMEND_LIST: (state, recommendList) => {
      recommendList.forEach(recommend => {
        state.recommendList.push(recommend);
      });
    },
  },
  actions: {
    async choiceDeal({ commit }, amountInfo){
      await amountDeal(amountInfo, 
      ({data}) => {
        console.log(data);
        if (data.message === "success") {
          commit("SET_ANSWER1", data.dealAmount);
          commit("SET_LOW", data.low);
          commit("SET_HIGH", data.high);

        } else {
          alert("적절한 자취방이 없습니다.");
        }
      },
      (error) => {
        console.log(error);
      });
    },
    async choiceRent({ commit }, amountInfo){
      await amountRent(amountInfo, 
      ({data}) => {
        if (data.message === "success") {
          commit("SET_ANSWER2", data.rentMoney);
          commit("SET_LOW", data.low);
          commit("SET_HIGH", data.high);

        } else {
          alert("적절한 자취방이 없습니다.");
        }
      },
      (error) => {
        console.log(error);
      });
    },
    async choiceLocation({ commit }, amountInfo){
      await location(amountInfo, 
      ({data}) => {
        if (data.message === "success") {
          commit("SET_ANSWER3", data.distance);

        } else {
          alert("적절한 자취방이 없습니다.");
        }
      },
      (error) => {
        console.log(error);
      });
    },
    //바꿔야함
    async lastchoice({ state, commit }, amountInfo){
      await finish(amountInfo, 
      ({data}) => {
        if (data.message === "success") {
          console.log("data", data);
          commit("CLEAR_RECOMMEND_LIST");
          commit("SET_RECOMMEND_LIST", data.houses);
          console.log("rl", state.recommendList);
        } else {
          alert("적절한 자취방이 없습니다.");
        }
      },
      (error) => {
        console.log(error);
      });
    },
  },
}

export default recommendStore;
