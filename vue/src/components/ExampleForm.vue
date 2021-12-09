<template>
  <div>
    <form>
      <div>
        <label for="title">Title</label>
        <input type="text" id="title" v-model="example.title" />
      </div>

      <div>
        <label for="snippet">Snippet</label>
        <textarea id="snippet" v-model="example.snippet"></textarea>
      </div>

      <div>
        <label for="languageType">Language</label>
        <select v-model="example.languageType">
          <option value="">Show All</option>
          <option v-for="language in languages" v-bind:key="language.id" v-bind:value="language.type">{{language.type}}</option>
        </select>
      </div>

      <div>
        <span>Tags:</span>

        <label for="Loops">Loops</label>
        <input
          v-model="example.tagList"
          type="checkbox"
          id="Loops"
          name="Loops"
          v-bind:value="{ name: 'Loops' }"
        />

        <label for="Branches">Branches</label>
         <input
          v-model="example.tagList"
          type="checkbox"
          id="Branches"
          name="Branches"
          v-bind:value="{ name: 'Branches' }"
        />

        <label for="Arrays">Arrays</label>
        <input
          v-model="example.tagList"
          type="checkbox"
          id="Arrays"
          name="Arrays"
          v-bind:value="{ name: 'Arrays' }"
        />
        
    
        <label for="Other">Other</label>
        <input
          v-model="example.tagList"
          type="checkbox"
          id="Other"
          name="Other"
          v-bind:value="{ name: 'Other' }"
        />
        
      </div>

      <button @click.prevent="addExample">Submit</button>
    </form>
  </div>
</template>

<script>
import exampleService from "../services/ExampleService";
import languageService from '../services/LanguageService';

export default {
  data() {
    return {
      example: {
        title: "",
        snippet: "",
        languageType: "",
        tagList: [],
      },
      languages: []
    };
  },
  methods: {
    addExample() {
      exampleService
        .addExample(this.example)
        .then((response) => {
          if (response.status == 201) {
            this.$router.push("/");
          }
        })
        .catch((error) => {
          if (error.response.status == 400) {
            // TODO: Route user to error page
            console.error(error);
          } else {
            console.error(error);
          }
        });
    },
  },
  created() {
    languageService.getAllLanguages().then(response => {
      this.languages = response.data.filter(language => !language.deleted);
    }).catch(error => {
      console.error(error);
    });
  }
};
</script>

<style scoped>
    label {
        display: inline-block;
        vertical-align: top;
        width: 10ch;
        text-align: right;
        padding-right: 4px;
    }

    textarea {
        height: 4rem;
        width: 60ch;
    }

    span {
        display: inline-block;
        vertical-align: top;
        width: 10ch;
        text-align: right;
        padding-right: 4px;
    }
</style>