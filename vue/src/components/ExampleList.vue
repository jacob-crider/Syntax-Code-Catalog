<template>
  <div>
    <example
      v-for="example in examples"
      v-bind:example="example"
      v-bind:key="example.example_id"
    />
  </div>
</template>

<script>
import exampleService from "../services/ExampleService";
import Example from "./Example.vue";

export default {
  components: { Example },
  name: "ExampleList",
  data() {
    return {
      examples: [],
    };
  },
  created() {
    exampleService
      .getAllExamples()
      .then((response) => {
        this.examples = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  }
};
</script>

<style></style>
