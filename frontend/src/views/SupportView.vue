<template>
  <div>
  <v-app id="inspire">
    <v-card
      flat
      tile
    >
      <v-container fluid>
        <v-subheader>금융 지원</v-subheader>
  
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <v-img
                :src="`https://theyoungnews.com/wp-content/uploads/2021/02/%EC%A0%9C%EB%AA%A9%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94.jpg`"
                height="300px"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
  
              <v-card-actions class="white justify-center">
                <v-btn color="indigo"
                  class="white--text"
                  fab
                  icon large
                >
                  <v-icon>mdi-mouse</v-icon> 방문하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <v-img
                :src="`https://theyoungnews.com/wp-content/uploads/2021/03/%EC%A0%9C%EB%AA%A9%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94-2.jpg`"
                height="300px"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
  
              <v-card-actions class="white justify-center">
                <v-btn color="indigo"
                  class="white--text"
                  fab
                  icon large
                >
                  <v-icon>mdi-mouse</v-icon> 방문하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <v-img
                :src="`https://theyoungnews.com/wp-content/uploads/2021/02/%EC%A0%9C%EB%AA%A9%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94-9.jpg`"
                height="300px"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
  
              <v-card-actions class="white justify-center">
                <v-btn color="indigo"
                  class="white--text"
                  fab
                  icon large
                >
                  <v-icon>mdi-mouse</v-icon> 방문하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>

      <v-container fluid>
        <v-subheader>주거 지원</v-subheader>
  
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <v-img
                :src="`https://theyoungnews.com/wp-content/uploads/2021/06/lh-%EC%B2%AD%EB%85%84-%EC%A0%84%EC%84%B8-%EC%9E%84%EB%8C%80-%EC%A3%BC%ED%83%9D.jpg`"
                height="300px"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
  
              <v-card-actions class="white justify-center">
                <v-btn color="indigo"
                  class="white--text"
                  fab
                  icon large
                >
                  <v-icon>mdi-mouse</v-icon> 방문하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <v-img
                :src="`https://theyoungnews.com/wp-content/uploads/2021/06/%EC%A0%9C%EB%AA%A9%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%95%B4%EC%A3%BC%EC%84%B8%EC%9A%94-6.jpg`"
                height="300px"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
  
              <v-card-actions class="white justify-center">
                <v-btn color="indigo"
                  class="white--text"
                  fab
                  icon large
                >
                  <v-icon>mdi-mouse</v-icon> 방문하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <v-img
                :src="`https://blog.kakaocdn.net/dn/bSm1gy/btrLzRCRPdM/BLacEZY8KJKFnPtyLimZDK/img.png`"
                height="300px"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
  
              <v-card-actions class="white justify-center">
                <v-btn color="indigo"
                  class="white--text"
                  fab
                  icon large
                >
                  <v-icon>mdi-mouse</v-icon> 방문하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-app>
</div>
</template>

<script>
export default {
    name : "SupportView",
    data() {
      return {
        link1: "https://www.google.co.kr/",
        link2: "https://www.naver.com/",
        link3: "https://edu.ssafy.com/",
        types: ['금융 지원', '주거 지원'],
      cards: ['Good', 'Best', 'Finest'],
      socials: [
      {
        icon: 'mdi-mouse',
        color: 'indigo',
      },
    ],
      }
    },
    methods : {
      toLink(link){
        window.open(link, '_blank');
      },
    }

    
}

</script>

<style>

</style>