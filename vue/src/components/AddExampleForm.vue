<template>
  <div>
    <h1>Add Example</h1>
    <form class="snippet-form">
      <div class="snippet-input">
        <label for="title">Title</label>
        <input
          type="text"
          id="title"
          v-model="example.title"
          placeholder="Title"
        />
      </div>

      <div class="snippet-input">
        <label for="snippet">Snippet</label>
        <textarea
          id="snippet"
          v-model="example.snippet"
          placeholder="Code snippet"
        ></textarea>
      </div>

      <div class="snippet-input">
        <label for="description">Description</label>
        <textarea
          id="description"
          v-model="example.description"
          placeholder="Description of code snippet"
        ></textarea>
      </div>

      <div class="snippet-input">
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

      <div class="snippet-input">
        <label for="language-tags">Tags:</label>
        <input
          type="text"
          id="language-tags"
          v-model="tag"
          placeholder="Separated by spaces"
        />
      </div>

      <div class="snippet-input">
        <img
          v-if="example.imageUrl !== ''"
          :src="example.imageUrl"
          alt="Upload an image..."
        />
      </div>

      <div class="upload-button">
        <button @click.prevent="uploadImage">Upload Image</button>
      </div>

      <div class="submit-button">
        <button @click.prevent="addExample">Submit</button>
      </div>
    </form>
  </div>
</template>

<script>
import exampleService from "../services/ExampleService";
import languageService from "../services/LanguageService";

export default {
  name: "AddExampleForm",
  data() {
    return {
      example: {
        title: "",
        snippet: "",
        languageType: "",
        description: "",
        imageUrl: "",
        tagList: [],
      },
      languages: [],
      tag: "",
    };
  },
  methods: {
    addExample() {
      if (this.tag !== "") {
        let arrayOfTags = this.tag.split(" ");
        this.example.tagList = arrayOfTags.map((tag) => ({
          name: tag,
        }));
      }
      exampleService
        .addExample(this.example)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push("/");
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
    uploadImage() {
      window.cloudinary.openUploadWidget(
        {
          cloudName: "syntax-image",
          uploadPreset: "fo0weqjc",
          sources: ["local", "url"],
        },
        (error, result) => {
          if (!error && result?.event === "success") {
            this.example.imageUrl = result.info.secure_url;
          }
        }
      );
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
  },
};
</script>

<style scoped>
.snippet-form {
  width: 640px;
  margin: auto;
  background-color: var(--form-color);
  padding: 32px;
  border-radius: 4px;
}

.snippet-input {
  margin: 32px 0;
  line-height: 1.5;
}

img {
  height: 100px;
}

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
  border-radius: 4px;
}

span {
  display: inline-block;
  vertical-align: top;
  width: 10ch;
  text-align: right;
  padding-right: 4px;
}

select {
  border-radius: 4px;
  padding: 6px;
}

.submit-button button {
  display: block;
  margin: auto;
}
</style>
