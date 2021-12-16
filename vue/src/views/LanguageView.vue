<template>
  <div class="language-view">
    <h1>Language Editor</h1>
    <add-language />
    <div class="spacer" />
    <language-list />
  </div>
</template>

<script>
import LanguageList from '../components/LanguageList.vue';
import AddLanguage from '../components/AddLanguage.vue';

export default {
  name: 'LanguageView',
  components: {
    LanguageList,
    AddLanguage,
  },
};
</script>

<style scoped>
div.language-view {
  text-align: center;
}

div.spacer {
  margin: 32px;
}
</style>
