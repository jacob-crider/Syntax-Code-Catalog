<template>
  <div class="card">
    <h1>{{ example.title }}</h1>
    <p>{{ example.languageType }}</p>
    <p v-if="example.tagList.length !== 0">Tags: <span v-for="tag in example.tagList" v-bind:key="tag.id">{{tag.name + " "}}</span> </p>
    <div>
      <pre><code class="language-js">{{ example.snippet }}</code></pre>
    </div>
  </div>
</template>

<script>

export default {
  name: "example",
  props: ["example"],

};
</script>

<style>
div.card {
  margin: 20px 0;
  padding: 16px;
  background-color: #ddd;
  border-radius: 4px;
}

  span {

  }
</style>
