import { sidoList, gugunList, dongList, houseList, houseDetail } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    houseMakers: [],
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_HOUSE_MAKERS(state) {
      state.houseMakers = [];
    },
    SET_SIDO_LIST(state, sidos) {
      console.log("sidos: ", sidos);
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido, text: sido });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun, text: gugun });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong, text: dong });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_HOUSE_MAKERS(state, houses) {
      console.log(houses.length);
      houses.forEach((house) => {
        state.houseMakers.push([house.lat, house.lng, house.aptcode]);
      });
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          console.log("data: ", data);
          data = data.sidoList;
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          data = data.gugunList;
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, sidoAndGugun) => {
      console.log("sidoAndGugun", sidoAndGugun);
      const params = sidoAndGugun;
      dongList(
        params,
        ({ data }) => {
          data = data.dongList;
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getHouseList ({ commit, state }, locationInfo) {
      const params = locationInfo;
      await houseList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data.houses);
          commit("CLEAR_HOUSE_MAKERS");
          commit("SET_HOUSE_MAKERS", data.houses);
          console.log(state.houses);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async detailHouse ({ commit, state }, locationInfo) {
      // 나중에 house.일련번호를 이용하여 API 호출
      await houseDetail(
        locationInfo,
        ({ data }) => {
          commit("SET_DETAIL_HOUSE", data.houses[0]);
          commit("CLEAR_HOUSE_MAKERS");
          commit("SET_HOUSE_MAKERS", [data.houses[0]]);
          console.log("1개", state.house);
        },
        (error) => {
          console.log(error);
        }
      )
    },
  },
};

export default houseStore;
