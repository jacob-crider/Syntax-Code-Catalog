<template>
  <div class="compiler">
    <h1>Syntax Compiler</h1>
    <div class="view">
      <textarea v-model="execution.script" cols="100" rows="30"></textarea>
      <div>
        <label for="language">Language</label>
        <select id="language" v-model="execution.language">
          <option value="">Show All</option>
          <option value="java">Java</option>
          <option value="c">C</option>
          <option value="cpp">C++</option>
          <option value="php">PHP</option>
          <option value="perl">Perl</option>
          <option value="python2">Python 2</option>
          <option value="ruby">Ruby</option>
          <option value="go">GO Lang</option>
          <option value="scala">Scala</option>
          <option value="bash">Bash Shell</option>
          <option value="sql">SQL</option>
          <option value="pascal">Pascal</option>
          <option value="csharp">C#</option>
          <option value="objc">Objective C</option>
          <option value="swift">Swift</option>
          <option value="groovy">Groovy</option>
          <option value="fortran">Fortran</option>
          <option value="hack">Hack</option>
          <option value="rust">RUST</option>
          <option value="r">R Language</option>
          <option value="freebasic">FREE BASIC</option>
          <option value="yabasic">YaBasic</option>
          <option value="clojure">Clojure</option>
          <option value="nodejs">NodeJS</option>
          <option value="scheme">Scheme</option>
          <option value="coffeescript">coffeeScript</option>
          <option value="nasm">Assembler - NASM</option>
          <option value="gccasm">Assembler - GCC</option>
          <option value="rhino">Rhino JS</option>
          <option value="elixir">Elixir</option>
          <option value="kotlin">Kotlin</option>
          <option value="whitespace">Whitespace</option>
        </select>
        <button v-bind:disabled="!execution.language" class="run-button" @click="compile(execution)">RUN</button>
      </div>

      <p>output: {{ compiledResponse.output }}</p>
      <p>memory: {{ compiledResponse.memory }}</p>
      <p>cpu time: {{ compiledResponse.cpuTime }}</p>
    </div>
  </div>
</template>

<script>
import jDoodleService from "../services/jDoodleService";

export default {
  name: "Compiler",
  data() {
    return {
      execution: {
        clientId: "3e61a4db2b176eb8ffc346541620be10",
        clientSecret:
          "78521e98f6289691b6b48f47b329dc9e456a0ee1a2600073c7bd2116d55344a6",
        script: "",
        language: "",
        versionIndex: "0",
      },
      compiledResponse: {
        output: "",
        statusCode: "",
        memory: "",
        cpuTime: "",
      },
    };
  },
  methods: {
    compile(execution) {
      jDoodleService
        .compile(execution)
        .then((response) => {
          this.compiledResponse.output = response.data.output;
          this.compiledResponse.statusCode = response.data.statusCode;
          this.compiledResponse.memory = response.data.memory;
          this.compiledResponse.cpuTime = response.data.cpuTime;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style>
.compiler {
  width: 80%;
  margin: auto;
  background-color: var(--card-color);
  padding: 16px;
  border-radius: 4px;
}

.run-button {
  display: inline;
  margin-left: 490px;
}

.view {
  width: 80%;
  margin: auto;
}
</style>