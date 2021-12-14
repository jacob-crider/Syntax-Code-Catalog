<template>
  <div>
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
table {
  table-layout: auto;
  width: 100%;
  max-width: 480px;
  margin: auto;
  padding: 10px;
  background-color: var(--form-dark);
  border-radius: 4px;
}
</style>
