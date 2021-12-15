<template>
  <div>
    <form>
      <div>
        <label for="language-type">Language</label>
        <input id="language-type" type="text" v-model="language.type" />
        <button @click.prevent="addNewLanguage">Submit</button>
      </div>
    </form>
  </div>
</template>

<script>
import languageService from '../services/LanguageService';

export default {
  name: 'AddLanguage',
  data() {
    return {
      language: {
        type: '',
      },
    };
  },
  methods: {
    addNewLanguage() {
      languageService.addLanguage(this.language).then((response) => {
        if (response.status === 201) {
          this.language.type = '';
          alert('Language added successfully!');
          this.$router.go(this.$router.currentRoute);
        }
      }).catch((error) => {
        alert('Could not add language.');
        console.error(error);
      });
    },
  },
};
</script>

<style scoped>
form {
  width: 100%;
  max-width: 480px;
  margin: auto;
  padding: 10px;
  border-radius: 4px;
  background-color: var(--form-color);
  line-height: 1.5;
}

input {
  margin: 0 8px;
  width: 60%;
}
</style>
