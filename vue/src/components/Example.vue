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
        <img class="copy" src="copy.png" alt="copy to clipboard" title="Copy To Clipboard"/>
      </button>
    </div>
    <p>{{ example.languageType }}</p>

    <code-highlight>{{ example.snippet }}</code-highlight>
    <img class="diagram" v-if="example.imageUrl !== null" :src="example.imageUrl" alt="snippet image" />
    <p>{{ example.description }}</p>
    <button @click.prevent="toggle = !toggle">EMAIL</button>
    <email-snippet v-if="toggle" v-bind:example="example"></email-snippet>
    <div>
      <button id="edit-button" @click.prevent="showForm = !showForm" v-if="isAdmin">EDIT</button>
    </div>

    <edit-example-form v-if="showForm" v-bind:example="example" />
    <comment-list v-bind:example="example"/>
  </div>
</template>

<script>
import { component as CodeHighlight } from 'vue-code-highlight';
import EditExampleForm from "./EditExampleForm.vue";
import EmailSnippet from "./EmailSnippet.vue";
import CommentList from './CommentList.vue'

export default {
  name: "example",
  props: ["example"],
  components: {
    CodeHighlight,
    EditExampleForm,
    EmailSnippet,
    CommentList
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
h2 {
  text-align: left;
}

div.card {
  width: 54ch;
  margin: 32px 0;
  padding: 16px;
  background-color: var(--card-color);
  border-radius: 4px;
}

span.tag {
  padding: 0 4px;
  border-right: 1px solid var(--text-color);
}

span.tag:last-child {
  border-right: none;
}

img.copy {
  width: 16px;
  height: 16px;
}

.copy-div {
  float: right;
}

.copy-div button {
  background-color: inherit;
  padding: 2px;
}

p.tags {
  float: right;
}

img.diagram {
  display: block;
  max-height: 200px;
  width: auto;
  max-width: 90%;
  margin: 16px 0;
}

#edit-button {
  margin: 16px 0;
}
</style>
