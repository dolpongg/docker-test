<template>
      <v-app id="inspire" style="margin-top:20%">
        <v-card elevation="7" shaped style="margin: 0 400px 0 400px" height="300px">
          <v-card-title>로그인</v-card-title>
          <v-form>
            <v-container style="padding: 20px 100px 0 100px;">
              <v-row>
                <v-col cols="12" sm="9">
                  <v-row>
                    <v-col>
                      <v-text-field
                        label="Id"
                        outlined
                        placeholder="Id"
                        v-model="user.id"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-text-field
                        label="Password"
                        type="password"
                        outlined
                        placeholder="Password"
                        v-model="user.pass"
                        @keyup.enter="confirm"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              
                <v-col cols="12" sm="3">
                  <!-- <v-img src="@/assets/jj.jpg" max-width="50px" max-height="50px"/> -->
                  <v-row style="padding: 15px 0 0 30px;">
                    <b-button size="lg" @click="confirm" style="width:200px">로그인</b-button>
                  </v-row>
                  <v-row style="padding: 65px 0 0 30px;">
                    <b-button size="lg" @click="movePage" style="width:200px">회원가입</b-button>
                  </v-row>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </v-card>
      </v-app>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name : "LoginView",
  // vuetify: new Vuetify(),
  data() {
    return {
      user: {
        id: null,
        pass: null,
      },
    }
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      console.log(this.user);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "index" });
      }
    },
    movePage() {
      this.$router.push({ name: "regist" });
    },
  },
}
</script>

<style scoped>
  #inspire {
    text-align: center;
    background-color: rgb(244,243,238);
  }
</style>