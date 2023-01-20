<template>
  <v-app>
          <v-navigation-drawer app color="rgb(17, 45, 78)" width="10%" expand-on-hover>
            <router-link to="/">
              <v-img src="@/assets/logo.png" style="margin-top:20px;"/>
            </router-link>
            <v-divider></v-divider>
            <v-list
              dense
              nav
            >
              <v-list-item
                v-for="item in items"
                :key="item.title"
                class="menubox"
              >
                <v-list-item-icon>
                  <v-icon color="rgb(229,166,113)">{{ item.icon }}</v-icon>
                </v-list-item-icon>
    
                <v-list-item-content>
                  <router-link :to="item.to">
                  <v-list-item-title @click="startRecommend(info)" class="bar">{{ item.title }}</v-list-item-title>
                  </router-link>
                </v-list-item-content>
              </v-list-item>
            </v-list>

            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <v-list nav dense>
              <header-view></header-view>

            </v-list>
          </v-navigation-drawer>
          <v-main>
            <div style="position:relative">
              <router-view ></router-view>
            </div>
          </v-main>
  </v-app>
</template> 
<script>
import HeaderView from '@/views/HeaderView.vue';
import { mapState, mapActions,  } from "vuex";
const recommendStore = "recommendStore";

export default {
  name: 'App',
  components : {
    HeaderView,
  },
  data() {
    return {
      info : {
        now: "0",
        low: "0",
        high: "0",
        flag: "start",
      },
      items: [
          { title: '매물 검색', icon: 'mdi-view-dashboard', to:"/main"},
          { title: '매물 추천', icon: 'mdi-image', to:"/recommend/question1"},
          { title: '지원 받기', icon: 'mdi-umbrella', to:"/support" },
          { title: '게시판', icon: 'mdi-developer-board', to:"/board" },
        ],
        right: null,
    }
  },
  computed: {
    ...mapState(recommendStore, ["answer1", "low", "high"]),
  },
  methods: {
    ...mapActions(recommendStore, ["choiceDeal"]),
    startRecommend(){
      this.choiceDeal(this.info);
      // this.$router.push({name : "question1"});
    },
  },
};
</script>
/deep/
<style>
 .bar {
    color: rgb(244,243,238);
  }
  .v-main{
    background-color: rgb(244,243,238);
  }
  .menubox:hover{
    background-color : rgb(32, 84, 144);
    color : rgb(244,243,238);
  }
</style>
