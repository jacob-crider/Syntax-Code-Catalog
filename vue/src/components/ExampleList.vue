<template>
  <div>
    <div class="filters">
      <label for="title">Filter by title</label>
      <input v-model="filter.title" type="text" name="title" />
    </div>
    <div class="filters">
      <label for="language">Filter by language</label>
      <input v-model="filter.language" type="text" name="language" />
    </div>
    <div class="filters">
      <label for="tag">Filter by tag</label>
      <input v-model="filter.tag" type="text" name="tag" />
    </div>

    <example
      v-for="example in filteredList"
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
      filter: {
        title: "",
        language: "",
        tag: "",
      },
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
  },
  computed: {
    filteredList() {
      let filteredExamples = this.examples;
      if (this.filter.title != "") {
        filteredExamples = filteredExamples.filter((example) =>
          example.title.toLowerCase().includes(this.filter.title.toLowerCase())
        );
      }
      if (this.filter.language != "") {
        filteredExamples = filteredExamples.filter((example) =>
          example.languageType
            .toLowerCase()
            .includes(this.filter.language.toLowerCase())
        );
      }
      if (this.filter.tag != "") {
        filteredExamples = filteredExamples.filter((example) => {
          let isFound = false;
          example.tagList.forEach((element) => {
            if (
              element.name.toLowerCase().includes(this.filter.tag.toLowerCase())
            ) {
              isFound = true;
            }
          });
          return isFound;
        });
      }
      return filteredExamples;
    },
  },
};
</script>

<style scoped>
  div.filters {
    display: inline-block;
    margin: 5px;
  }
</style>
