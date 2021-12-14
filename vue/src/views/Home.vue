<template>
  <div class="home">
    <h1 class="title">Code Snippets</h1>
    <p class="title">Code your heart away!</p>
    <iframe src="https://codesandbox.io/embed/solitary-water-ys3iu?fontsize=14&hidenavigation=1&theme=dark"
     style="width:100%; height:500px; border:0; border-radius: 4px; overflow:hidden;"
     title="solitary-water-ys3iu"
     allow="accelerometer; ambient-light-sensor; camera; encrypted-media; geolocation; gyroscope; hid; microphone; midi; payment; usb; vr; xr-spatial-tracking"
     sandbox="allow-forms allow-modals allow-popups allow-presentation allow-same-origin allow-scripts"
   ></iframe>
    <router-link tag="button" to="/addExample">Add Snippet</router-link>
    <example-list />
  </div>
</template>

<script>
import ExampleList from "../components/ExampleList.vue";

export default {
  components: { ExampleList },
  name: "home",
};
</script>

<style>
.title {
  text-align: center;
}
</style>
