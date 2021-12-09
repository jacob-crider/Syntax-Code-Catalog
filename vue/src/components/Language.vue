<template>
  <tr>
    <td>
      <input type="text" v-model="currentLanguage.type" />
    </td>

    <td>
      <input
          type="checkbox"
          v-bind:checked="!currentLanguage.deleted"
          v-bind:value="currentLanguage.deleted"
          v-on:change="handleChangeChecked"
      />
    </td>
    <td>
      <button v-on:click.prevent="handleClickSave">Save</button>
    </td>
  </tr>
</template>

<script>
import languageService from '../services/LanguageService';

export default {
  name: 'Language',
  props: ['language'],
  data() {
    return {
      currentLanguage: {
        type: '',
        id: 0,
        deleted: false,
      },
    };
  },
  created() {
    this.currentLanguage = this.language;
  },
  methods: {
    handleChangeChecked() {
      this.currentLanguage.deleted = !this.currentLanguage.deleted;
    },
    handleClickSave() {
      languageService.updateLanguage(this.currentLanguage).then(response => {
        if (response.status === 200) {
          alert('Language saved!');
        }
      }).catch(error => {
        alert('Language not saved!');
        console.error(error);
      });
    },
  },
};
</script>

<style scoped>
td {
  line-height: 1.5;
}

input {
  width: 100%;
}

button {
  width: 100%;
}
</style>
