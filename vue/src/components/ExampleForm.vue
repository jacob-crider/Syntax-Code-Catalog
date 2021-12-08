<template>
  <div>
      <form>
          <label for="title">Title</label>
          <input type="text" id="title" v-model="example.title">

          <label for="snippet">Snippet</label>
          <textarea id="snippet" v-model="example.snippet"></textarea>

          <label for="languageType">Language</label>
          <select v-model="example.languageType">
            <option value="">Show All</option>
            <option >JavaScript</option>
            <option >Java</option>
            <option >SQL</option>
          </select>
          
          <p>Tags</p>
          <input v-model="example.tagList" type="checkbox" id="Loops" name="Loops" v-bind:value="{name: 'Loops'}">
            <label for="Loops">Loops</label>
          <input v-model="example.tagList" type="checkbox" id="Branches" name="Branches" v-bind:value="{name: 'Branches'}">
            <label for="Branches">Branches</label>
          <input v-model="example.tagList" type="checkbox" id="Arrays" name="Arrays" v-bind:value="{name: 'Arrays'}">
            <label for="Arrays">Arrays</label>
          <input v-model="example.tagList" type="checkbox" id="Other" name="Other" v-bind:value="{name: 'Other'}">
            <label for="Other">Other</label>      

          <button @click.prevent="addExample">Submit</button>
      </form>
  </div>
</template>

<script>
import exampleService from '../services/ExampleService';

export default {
    data() {
        return {
            example: {
                title: '',
                snippet: '',
                languageType: '',
                tagList: []
            }
        }
    }, 
    methods: {
        addExample() {
            exampleService.addExample(this.example).then(response => {
                if (response.status == 201) {
                    this.$router.push('/');
                }
            }).catch(error => {
                if (error.response.status == 400) {
                    // TODO: Route user to error page
                    console.error(error);
                } else {
                    console.error(error);
                }
            });
        },
    }
}
</script>

<style>

</style>