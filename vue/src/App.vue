<template>
  <div id="app">
    <div class="content-box">
      <h1>Wine Pairing App</h1>
      <label for="foodInput">Enter a food item or cuisine:</label>
      <br>
      <input v-model="foodInput" id="foodInput" type="text">
      <br>
      <button v-on:click="fetchWineRecommendation">Get Wine Pairings</button>
      <WineRecommendation :wineRec="wineRec" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import WineRecommendation from './components/WineRecommendation.vue';

export default {
  name: 'App',
  components: {
    WineRecommendation,
  },
  data() {
    return {
      foodInput: 'Italian', // Default value, you can set it to an empty string if you prefer
      wineRec: null,
    };
  },
  methods: {
    async fetchWineRecommendation() {
      try {
        const response = await axios.get(
          `http://localhost:8080/getPairing?query=${this.foodInput}`
        );
        console.log(response.data); // Log the response to the console
        this.wineRec = response.data;
      } catch (error) {
        console.error('Error fetching wine recommendation:', error);
      }
    },
  },
};
</script>

<style>
#app {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}

.content-box {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  max-width: 600px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
}
</style>
