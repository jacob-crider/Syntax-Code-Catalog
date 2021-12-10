<template>
  <div class="card">
    <h2>{{ example.title }}</h2>
    <p>{{ example.languageType }}</p>
    <p v-if="example.tagList.length !== 0">Tags: <span class="tag" v-for="tag in example.tagList"
        v-bind:key="tag.id">{{ tag.name }}</span></p>
    <div>
      <pre><code>{{ example.snippet }}</code></pre>
    </div>
      <p>{{ example.description }}</p>
      <button @click.prevent="showForm = true" v-if="isAdmin">Edit</button>
      
      <edit-example-form v-if="showForm" v-bind:example="example" />
      
  </div>
</template>

<script>
import hljs from 'highlight.js/lib/common';
import EditExampleForm from './EditExampleForm.vue'

export default {
  name: 'example',
  props: ['example'],
  components: {
    EditExampleForm
  },
  mounted() {
    hljs.highlightAll();
  },
  computed: {
    isAdmin() {
      return (this.$store.state.token !== '') && (this.$store.state.user.authorities[0].name === 'ROLE_ADMIN');
    }
  },
  data() {
    return {
      showForm: false
    }
  }
};
</script>

<style scoped>
div.card {
  margin: 20px 0;
  padding: 16px;
  background-color: lightblue;
  border-radius: 4px;
}

span.tag {
  padding: 0 4px;
  border-right: 1px solid black;
}

span.tag:last-child {
  border-right: none;
}
</style>
