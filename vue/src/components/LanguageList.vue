<template>
  <div>
    <h2>Edit Languages</h2>
    <table>
      <thead>
      <tr>
        <th>Type</th>
        <th>Active</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <language
          v-for="currentLanguage in languages"
          v-bind:language="currentLanguage"
          v-bind:key="currentLanguage.id"
      />
      </tbody>
    </table>
  </div>
</template>

<script>
import Language from './Language.vue';
import languageService from '../services/LanguageService';

export default {
  name: 'LanguageList',
  components: {
    Language,
  },
  data() {
    return {
      languages: [],
    };
  },
  created() {
    languageService.getAllLanguages().then((response) => {
      this.languages = response.data;
    }).catch((error) => {
      console.error(error);
    });
  },
};
</script>

<style scoped>
div {
  max-width: 480px;
  margin: auto;
  padding: 32px;
  background-color: var(--form-color);
  border-radius: 4px;
}

table {
  table-layout: auto;
  width: 100%;
}
</style>
