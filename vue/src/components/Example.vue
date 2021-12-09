<template>
  <div class="card">
    <h2>{{ example.title }}</h2>
    <p>{{ example.languageType }}</p>
    <p v-if="example.tagList.length !== 0">Tags: <span class="tag" v-for="tag in example.tagList"
        v-bind:key="tag.id">{{ tag.name }}</span></p>
    <div>
      <pre><code>{{ example.snippet }}</code></pre>
    </div>
  </div>
</template>

<script>
import hljs from 'highlight.js/lib/common';

export default {
  name: 'example',
  props: ['example'],
  mounted() {
    hljs.highlightAll();
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
