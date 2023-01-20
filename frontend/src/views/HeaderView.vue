<template>
  <div>
    <v-list-item v-if="userInfo">
      <v-list-item-icon>
        <v-icon color="rgb(229,166,113)">mdi-logout</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
          <v-list-item-title @click.prevent="onClickLogout" class="bar">로그아웃</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
    <v-list-item v-else>
      <v-list-item-icon>
        <v-icon color="rgb(229,166,113)">mdi-account-key</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
          <router-link to="/login">
          <v-list-item-title class="bar">로그인</v-list-item-title>
          </router-link>
      </v-list-item-content>
    </v-list-item>
    <v-list-item v-if="userInfo">
      <v-list-item-icon>
        <v-icon color="rgb(229,166,113)">mdi-bulletin-board</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
        <router-link to="/mypage">
          <v-list-item-title class="bar">마이페이지</v-list-item-title>
          </router-link>
      </v-list-item-content>
    </v-list-item>
    <v-list-item v-else>
      <v-list-item-icon>
        <v-icon color="rgb(229,166,113)">mdi-account-plus</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
          <router-link to="/regist">
          <v-list-item-title class="bar">회원가입</v-list-item-title>
          </router-link>
      </v-list-item-content>
    </v-list-item>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name : "HeaderView",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.id);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.id);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "index" });
    },
  },
}
</script>

<style>

</style>