<template>
  <div>
    <v-container fluid style="width: 100%;max-width: 100%;">
      <v-row>
        <v-col>
          <house-info />
        </v-col>
        <v-col>
          <house-map />
        </v-col>
      </v-row>

    </v-container>
  </div>
</template>

<script>
import HouseMap from '@/components/house/HouseMap.vue';
import HouseInfo from '@/components/house/HouseInfo.vue';

export default {
  name : "HouseView",
  components : {
    HouseMap,
    HouseInfo,
  }
}
</script>

<style>
  
</style>