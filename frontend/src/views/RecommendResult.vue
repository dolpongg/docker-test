<template>
  <div>
    <v-container fluid style="width: 100%;max-width: 100%;">
      <v-row>
        <v-col>
          <recommend-info />
        </v-col>
        <v-col>
          <recommend-map />
        </v-col>
      </v-row>

    </v-container>
  </div>
</template>

<script>
import RecommendInfo from '@/components/recommend/RecommendInfo'
import RecommendMap from '@/components/recommend/RecommendMap'
export default {
    name : "RecommendResult",
    components: {
        RecommendInfo,
        RecommendMap,
    }
}
</script>

<style>

</style>