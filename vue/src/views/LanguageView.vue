<template>
  <div>
    <h1>Language Editor</h1>
    <h2>Add Language</h2>
    <add-language />
    <h2>Edit Languages</h2>
    <language-list />
  </div>
</template>

<script>
import LanguageList from "../components/LanguageList.vue";
import AddLanguage from "../components/AddLanguage.vue"

export default {
  name: "LanguageView",
  components: {
    LanguageList,
    AddLanguage
  },
};
</script>

<style>
</style>