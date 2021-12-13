<template>
  <div class="home">
    <h1 class="title">Code Snippets</h1>
    <p class="title">Code your heart away!</p>
    
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
