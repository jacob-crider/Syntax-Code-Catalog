<template>
  <div>
    <form>
      <div>
        <label for="title">Title</label>
        <input type="text" id="title" v-model="editedExample.title" />
      </div>

      <div>
        <label for="snippet">Snippet</label>
        <textarea id="snippet" v-model="editedExample.snippet"></textarea>
      </div>

      <div>
        <label for="description">Description and Reference</label>
        <textarea id="description" v-model="editedExample.description"></textarea>
      </div>

      <div>
        <label for="languageType">Language</label>
        <select v-model="editedExample.languageType">
          <option value="">Show All</option>
          <option
            v-for="language in languages"
            v-bind:key="language.id"
            v-bind:value="language.type"
          >
            {{ language.type }}
          </option>
        </select>
      </div>

      <div>
        <span>Tags:</span>

        <input type="text" v-model="tag" />
      </div>

      <button @click.prevent="updateExample">Submit</button>
    </form>
  </div>
</template>

<script>
import exampleService from "../services/ExampleService";
import languageService from "../services/LanguageService";

export default {
  name: 'EditExampleForm',
  props: ['example'],
  data() {
    return {
      editedExample: {
        title: '',
        snippet: '',
        languageType: '',
        description: '',
        tagList: [],
      },
      languages: [],
      tag: '',
    };
  },
  methods: {
    updateExample() {
      if (this.tag != '') {
       let arrayOfTags = this.tag.split(" ");
       this.editedExample.tagList = arrayOfTags.map(tag => ({
           name: tag
       }));
      }
      exampleService
        .updateExample(this.editedExample)
        .then((response) => {
          if (response.status === 200) {
            this.$router.go(this.$router.currentRoute);
          }
        })
        .catch((error) => {
          if (error.response.status === 400) {
            // TODO: Route user to error page
            console.error(error);
          } else {
            console.error(error);
          }
        });
    },
  },
  created() {
    languageService
      .getAllLanguages()
      .then((response) => {
        this.languages = response.data.filter((language) => !language.deleted);
      })
      .catch((error) => {
        console.error(error);
      });
    
    this.editedExample = {...this.example};

  },
  
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