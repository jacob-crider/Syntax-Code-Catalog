<template>
  <div>
    <form>
      <div>
        <label for="type">Language</label>
        <input type="text" v-model="language.type" />
        <button @click.prevent="addNewLanguage">Submit</button>
      </div>
    </form>
  </div>
</template>

<script>
import languageService from "../services/LanguageService";

export default {
  name: "AddLanguage",
  data() {
    return {
      language: {
        type: "",
      },
    };
  },
  methods: {
    addNewLanguage() {
      languageService
        .addLanguage(this.language)
        .then((response) => {
          if (response.status == 201) {
            this.language.type = "";
            alert("Language added successfully!");
            this.$router.go(this.$router.currentRoute);
          }
        })
        .catch((error) => {
          alert("Could not add language.");
          console.error(error);
        });
    },
  },
};
</script>

<style>
</style>