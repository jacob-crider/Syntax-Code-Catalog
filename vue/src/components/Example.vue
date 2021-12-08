<template>
  <div>
    <h1>{{ example.title }}</h1>
    <p>{{ example.languageType }}</p>
    <p v-if="example.tagList.length !== 0">Tags: <span v-for="tag in example.tagList" v-bind:key="tag.id">{{tag.name + " "}}</span> </p> 
    <div>
      <pre><code>{{ example.snippet }}</code></pre>
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
  span {
    
  }
</style>
