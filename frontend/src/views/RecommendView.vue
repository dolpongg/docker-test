<template>
  <div>
    <v-card elevation="7" style="margin: 150px 10% 0 10%;" height="500px">
      <v-card-title>자취방 추천</v-card-title>
      <recommend-question style="margin:0 10% 0 10%"/>
    </v-card>
  </div>
</template>

<script>
import RecommendQuestion from "@/components/recommend/RecommendQuestion";
export default {
  name : "RecommendView",
  components : {
    RecommendQuestion,
  },
}
</script>

<style>

</style>