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
        <label for="description">Description</label>
        <textarea id="description" v-model="editedExample.description"></textarea>
      </div>

      <div>
        <label for="language-type">Language</label>
        <select id="language-type" v-model="editedExample.languageType">
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
        <label for="snippet-tags">Tags</label>
        <input id="snippet-tags" type="text" v-model="tag" />
      </div>

      <div>
        <img
            class="snippet-thumb"
            v-if="editedExample.imageUrl"
            :src="editedExample.imageUrl"
            alt="Upload an image..."
        />
      </div>

      <div class="upload-button">
        <button @click.prevent="uploadImage">Upload Image</button>
      </div>

      <button id="submit-button" @click.prevent="updateExample">Submit</button>
    </form>
  </div>
</template>

<script>
import exampleService from '../services/ExampleService';
import languageService from '../services/LanguageService';

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
        imageUrl: '',
        tagList: [],
      },
      languages: [],
      tag: '',
    };
  },
  methods: {
    updateExample() {
      if (this.tag !== '') {
        let arrayOfTags = this.tag.split(' ');
        this.editedExample.tagList = arrayOfTags.map(tag => ({
          name: tag,
        }));
      }
      exampleService.updateExample(this.editedExample).then((response) => {
        if (response.status === 200) {
          this.$router.go(this.$router.currentRoute);
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
      window.cloudinary.openUploadWidget(
          {
            cloudName: 'syntax-image',
            uploadPreset: 'fo0weqjc',
            sources: ['local', 'url'],
          },
          (error, result) => {
            if (!error && result?.event === 'success') {
              this.editedExample.imageUrl = result.info.secure_url;
            }
          },
      );
    },
  },
  created() {
    languageService.getAllLanguages().then((response) => {
      this.languages = response.data.filter((language) => !language.deleted);
    }).catch((error) => {
      console.error(error);
    });

    this.editedExample = {...this.example};
    this.tag = this.example.tagList.map(tag => {
      return tag.name;
    }).join(' ');
  },

};
</script>

<style scoped>
form > div {
  margin-top: 16px;
}

label {
  display: block;
}

select {
  border-radius: 4px;
  padding: 6px;
}

textarea {
  height: 4rem;
  width: 60ch;
  border-radius: 4px;
}

.snippet-thumb {
  height: 100px;
  margin: 16px 0;
}

.upload-button, #submit-button {
  margin: 16px 0;
}
</style>
