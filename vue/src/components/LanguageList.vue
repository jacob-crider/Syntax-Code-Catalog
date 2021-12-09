<template>
  <div>
      <language v-for="currentLanguage in languages" v-bind:language="currentLanguage" v-bind:key="currentLanguage.id"/>
  </div>
</template>

<script>
import Language from './Language.vue'
import languageService from '../services/LanguageService'

export default {
    name: 'LanguageList',
    components: {
        Language
    },
    data() {
        return {
            languages: []
        }
    },
    created() {
        languageService.getAllLanguages.
    }
}
</script>

<style>

</style>