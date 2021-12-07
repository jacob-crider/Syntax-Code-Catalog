<template>
  <div>
      <form>
          <label for="title">Title</label>
          <input type="text" id="title" v-model="example.title">

          <label for="snippet">Snippet</label>
          <textarea id="snippet" v-model="example.snippet"></textarea>

          <label for="languageType">Language</label>
          <select id="statusFilter" v-model="example.languageType">
            <option value="">Show All</option>
            <option >JavaScript</option>
            <option >Java</option>
            <option >SQL</option>
          </select>

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
                languageType: ''
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
        }
    }
}
</script>

<style>

</style>