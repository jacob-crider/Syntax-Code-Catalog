<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token !== ''"
      >Logout
      </router-link
      >&nbsp;|&nbsp;
      <router-link v-if="isAdmin" v-bind:to="{ name: 'language' }">
        Edit Languages
      </router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    isAdmin() {
      return (this.$store.state.token !== '') && (this.$store.state.user.authorities[0].name === 'ROLE_ADMIN');
    }
  }
}
</script>

<style>
html {
  font-family: "Roboto", sans-serif;
  box-sizing: border-box;
  background-color: rgba(44, 41, 37);
  color: #BC80EC ;
}

*, *:before, *:after {
  box-sizing: inherit;
}

div#app {
  width: 1000px;
  margin: auto;
}

button {
  padding: 4px;
  background-color: #BC80EC;
  border: none;
  border-radius: 2px;
}

button:hover {
  box-shadow: 1px 2px 1px black;
  cursor: pointer;
}
</style>
