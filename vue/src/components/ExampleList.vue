<template>
  <div class="example-list">
    <div class="filter-form">
      <router-link tag="button" to="/addExample">Add Snippet</router-link>
      <div class="filters">
        <input
          placeholder="Filter by title"
          v-model="filter.title"
          type="text"
          name="title"
        />
      </div>
      <div class="filters">
        <input
          placeholder="Filter by language"
          v-model="filter.language"
          type="text"
          name="language"
        />
      </div>
      <div class="filters">
        <input
          placeholder="Filter by tag"
          v-model="filter.tag"
          type="text"
          name="tag"
        />
      </div>
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

div.filter-form {
  background-color: #5450d8;
  border-radius: 4px;
  padding: 16px;
}

div.example-list{
  width: 80%;
  margin: auto;
}
</style>
