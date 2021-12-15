<template>
  <div class="compiler">
    <div class="input">
      <textarea v-model="execution.script" cols="80" rows="20"></textarea>
    </div>
    <button @click="compile(execution)">RUN</button>
    <div class="output">
      <p>output: {{ compiledResponse.output }}</p>
      <p>status code: {{ compiledResponse.statusCode }}</p>
      <p>memory: {{ compiledResponse.memory }}</p>
      <p>cpu time: {{ compiledResponse.cpuTime }}</p>
    </div>
  </div>
</template>

<script>
import jDoodleService from '../services/jDoodleService'

export default {
    name: 'Compiler',
  data() {
    return {
      execution: {
        clientId: "3e61a4db2b176eb8ffc346541620be10",
        clientSecret:
          "78521e98f6289691b6b48f47b329dc9e456a0ee1a2600073c7bd2116d55344a6",
        script: "",
        language: "java",
        versionIndex: "0",
      },
      compiledResponse: {
        output: '',
        statusCode: '',
        memory: '',
        cpuTime: ''
      },
    };
  },
  methods: {
    compile(execution) {
      jDoodleService.compile(execution).then(response => {
        this.compiledResponse.output = response.data.output;
        this.compiledResponse.statusCode = response.data.statusCode;
        this.compiledResponse.memory = response.data.memory;
        this.compiledResponse.cpuTime = response.data.cpuTime;
      }).catch(error => {
        console.error(error);
      });
    }
  }
};
</script>

<style>
  .compiler {
    width: 80%;
    margin: auto;
  }
</style>