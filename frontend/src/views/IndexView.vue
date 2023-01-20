<template>
  <div>
  <b-carousel
    id="carousel-fade"
    style="text-shadow: 0px 0px 2px #000"
    fade
    indicators
    img-width="1024"
    img-height="200"
  >
    <b-carousel-slide
      caption="First Slide"
      img-src="https://picsum.photos/1024/280/?image=10"
    ></b-carousel-slide>
    <b-carousel-slide
      caption="Second Slide"
      img-src="https://picsum.photos/1024/280/?image=12"
    ></b-carousel-slide>
    <b-carousel-slide
      caption="Third Slide"
      img-src="https://picsum.photos/1024/280/?image=22"
    ></b-carousel-slide>
  </b-carousel>


  <v-card
      flat
      tile
     style="background-color:rgb(244,243,238);">
      <v-container fluid>
  
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <router-link to="/main">
              <v-img
                src="@/assets/ji.png"
                height="300px"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
            </router-link>
            </v-card>
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <router-link to="/recommend/question1">
              <v-img
                src="@/assets/choo.png"
                height="300px"
                @click="startRecommend(info)"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
            </router-link>
            </v-card>
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-card>
              <router-link to="/support">
              <v-img
                src="@/assets/bok.png"
                height="300px"
              >
                <span
                  class="text-h5 white--text pl-4 pt-4 d-inline-block"
                ></span>
              </v-img>
            </router-link>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
</div>
  
</template>

<script>
export default {
  name : "IndexView",
  methods: {
    startRecommend(){
      this.choiceDeal(this.info);
      // this.$router.push({name : "question1"});
    },
  },
}
</script>

<style scoped>
  .main{
    background-color: rgb(244,243,238);
  }
  .v-parallax__image {
  transform: none !important;
  width: 100% !important;
}
</style>