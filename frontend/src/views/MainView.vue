<template>
  <div>
    <v-container fluid style="width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: 0;
    margin-left: 0;
    max-width:100%;">
      <v-row>
        <v-col style="padding-left:10%; padding-right: 10%;">
          <house-search-bar-vue></house-search-bar-vue>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <house-view></house-view>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import HouseView from '@/views/HouseView.vue';
import HouseSearchBarVue from '@/components/house/HouseSearchBar.vue';


export default {
  name : "MainView",
  components : {
    HouseView,
    HouseSearchBarVue,
  },
}
</script>

<style>

</style>