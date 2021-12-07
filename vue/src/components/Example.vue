<template>
  <div>
    <h1>{{ example.title }}</h1>
    <p>Language placeholder</p>
    <p>Tag placeholder</p>
    <p>Description of the code block placeholder</p>
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

<style></style>
