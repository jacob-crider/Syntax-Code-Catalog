<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token !== ''"
      >Logout
      </router-link
      >
    </div>
    <router-view />
  </div>
</template>

<style>
html {
  font-family: "Roboto", sans-serif;
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: inherit;
}

div#app {
  width: 1000px;
  margin: auto;
}
</style>
