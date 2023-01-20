<template>
  <Bar
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :plugins="plugins"
    :css-classes="cssClasses"
    :styles="styles"
    :width="width"
    :height="height"
  />
</template>

<script>
import { Bar } from 'vue-chartjs/legacy'
import { mapState } from 'vuex'
const houseStore = "houseStore";

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  name: 'RecommendDetailBar1',
  components: {
    Bar
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  props: {
    chartId: {
      type: String,
      default: 'bar-chart'
    },
    datasetIdKey: {
      type: String,
      default: 'label'
    },
    width: {
      type: Number,
      default: 400
    },
    height: {
      type: Number,
      default: 400
    },
    cssClasses: {
      default: '',
      type: String
    },
    styles: {
      type: Object,
      default: () => {}
    },
    plugins: {
      type: Array,
      default: () => []
    }
  },
  created() {
    this.chartData.datasets[0].data[0] = this.house.dealAmount;
    this.chartData.datasets[0].data[3] = this.house.dealAmount+500;
  },
  data() {
    return {
      chartData: {
        labels: [
          '이 집 보증금',
          '',
          '',
          '주변 보증금 평균',
          '',
          ''
        ],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: '#f87979',
            data: [0, 0, 0, 0, 0, 0]
          }
        ]
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      }
    }
  }
}
</script>