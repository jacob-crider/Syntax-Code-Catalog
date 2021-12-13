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
        <label for="description">Description and Reference</label>
        <textarea id="description" v-model="example.description"></textarea>
      </div>

      <div>
        <label for="language-type">Language</label>
        <select id="language-type" v-model="example.languageType">
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
        <label for="language-tags">Tags:</label>
        <input type="text" id="language-tags" v-model="tag" />
      </div>

      <div>
        <img :src="example.imageUrl" alt="Upload an image..." />
      </div>

      <button @click.prevent="uploadImage">Upload Image</button>

      <button @click.prevent="addExample">Submit</button>
    </form>
  </div>
</template>

<script>
import exampleService from '../services/ExampleService';
import languageService from '../services/LanguageService';

export default {
  name: 'AddExampleForm',
  data() {
    return {
      example: {
        title: '',
        snippet: '',
        languageType: '',
        description: '',
        imageUrl: '',
        tagList: [],
      },
      languages: [],
      tag: '',
    };
  },
  methods: {
    addExample() {
      if (this.tag !== '') {
        let arrayOfTags = this.tag.split(' ');
        this.example.tagList = arrayOfTags.map(tag => ({
          name: tag,
        }));
      }
      exampleService.addExample(this.example).then((response) => {
        if (response.status === 201) {
          this.$router.push('/');
        }
      }).catch((error) => {
        if (error.response.status === 400) {
          // TODO: Route user to error page
          console.error(error);
        } else {
          console.error(error);
        }
      });
    },
    uploadImage() {
      window.cloudinary.openUploadWidget({
        cloudName: 'syntax-image',
        uploadPreset: 'fo0weqjc',
        sources: ['local', 'url'],
      }, (error, result) => {
        if (!error && result?.event === 'success') {
          this.example.imageUrl = result.info.secure_url;
        }
      });
    },
  },
  created() {
    languageService.getAllLanguages().then((response) => {
      this.languages = response.data.filter((language) => !language.deleted);
    }).catch((error) => {
      console.error(error);
    });
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

img {
  height: 100px;
}
</style>
