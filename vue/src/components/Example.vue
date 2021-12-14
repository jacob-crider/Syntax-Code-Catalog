<template>
  <div class="card">
    <p class="tags">
      Tags:
      <span class="tag" v-for="tag in example.tagList" v-bind:key="tag.id">{{
        tag.name
      }}</span>
    </p>
    <h2>{{ example.title }}</h2>
     <div class="copy-div">
      <button @click.prevent="copyToClipBoard(example.snippet)">
        <img class="copy" src="copy.png" />
      </button>
    </div>
    <p>{{ example.languageType }}</p>

    <div class="copy-div">
      <button @click.prevent="copyToClipBoard(example.snippet)"><img class="copy" src="copy.png"></button>
    </div>
    <p v-if="example.tagList.length !== 0">Tags: <span class="tag" v-for="tag in example.tagList"
        v-bind:key="tag.id">{{ tag.name }}</span></p>

    <div>
      <code-highlight>{{ example.snippet }}</code-highlight>
      <button @click.prevent="toggle = !toggle">Share via Email</button>
      <email-snippet v-if="toggle" v-bind:example="example"></email-snippet>
    </div>
    <p>{{ example.description }}</p>
    <button @click.prevent="showForm = true" v-if="isAdmin">Edit</button>

    <edit-example-form v-if="showForm" v-bind:example="example" />
  </div>
</template>

<script>
import { component as CodeHighlight } from 'vue-code-highlight';
import 'vue-code-highlight/themes/prism-okaidia.css';
import EditExampleForm from "./EditExampleForm.vue";
import EmailSnippet from "./EmailSnippet.vue";

export default {
  name: "example",
  props: ["example"],
  components: {
    CodeHighlight,
    EditExampleForm,
    EmailSnippet,
  },
  computed: {
    isAdmin() {
      return (
        this.$store.state.token !== "" &&
        this.$store.state.user.authorities[0].name === "ROLE_ADMIN"
      );
    },
  },
  methods: {
    copyToClipBoard(snippet) {
      navigator.clipboard.writeText(snippet);
      alert('Copied Snippet');
    }
  },
  data() {
    return {
      showForm: false,
      toggle: false,
    };
  },
};
</script>

<style scoped>
div.card {
  margin: 20px 0;
  padding: 16px;
  background-color: #5450D8;
  border-radius: 4px;
}

span.tag {
  padding: 0 4px;
  border-right: 1px solid black;
}

span.tag:last-child {
  border-right: none;
}

.copy {
  width: 20px;
  height: 20px;
  align-content: right;
  border-radius: 8px;
  padding: 0px;
}

.copy-div {
  float: right;
}

p.tags {
  float: right;
}
</style>
