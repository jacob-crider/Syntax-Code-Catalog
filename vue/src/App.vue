<template>
  <div id="app">
    <div class="nav-bar">
      <router-link v-bind:to="{ name: 'home' }">
        <img id="logo-image" src="SyntaxWithColors.png" alt="Logo">
      </router-link>
      <div class="menu">
        <router-link class="menu-item" v-bind:to="{ name: 'logout' }" v-if="$store.state.token !== ''">
          Logout
        </router-link>
        <router-link class="menu-item" v-if="isAdmin" v-bind:to="{ name: 'language' }">
          Edit Languages
        </router-link>
      </div>
    </div>
    <div id="spacer"/>
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    isAdmin() {
      return (this.$store.state.token !== '') && (this.$store.state.user.authorities[0].name === 'ROLE_ADMIN');
    },
  },
};
</script>

<style>
:root {
  --rich-black-fogra-29: #0d1b2aff;
  --oxford-blue: #1b263bff;
  --bdazzled-blue: #415a77ff;
  --shadow-blue: #778da9ff;
  --platinum: #e0e1ddff;

  --bg-dark: var(--rich-black-fogra-29);
  --card-dark: var(--bdazzled-blue);
  --form-dark: var(--bdazzled-blue);
  --navbar-dark: var(--bdazzled-blue);
  --button: var(--shadow-blue);
  --input-dark: var(--platinum);
  --text-dark: var(--platinum);
}

html {
  font-family: "Montserrat", sans-serif;
  box-sizing: border-box;
  background-color: var(--bg-dark);
  color: var(--text-dark);
}

body {
  margin: 0;
}

h1, h2 {
  color: violet;
  font-weight: normal;
  text-align: center;
}

*, *:before, *:after {
  box-sizing: inherit;
}

a {
  color: var(--text-dark);
  text-decoration: none;
}

a:hover {
  color: var(--bg-dark);
  text-decoration: underline;
}

button {
  padding: 8px;
  background-color: var(--button);
  color: var(--text-dark);
  border: none;
  border-radius: 4px;
}

button:hover {
  box-shadow: 0 0 4px black;
  cursor: pointer;
}

input {
  line-height: 2;
  border-radius: 4px;
  border: 1px solid #cccccc;
  background-color: var(--input-dark);
}

#spacer {
  margin-top: 64px;
}

#logo-image {
  height: 50px;
}

.nav-bar {
  position: fixed;
  top: 0;
  left: 0;
  background-color: var(--card-dark);
  width: 100%;
}

.menu {
  float: right;
}

.menu-item {
  padding: 0 16px;
  border-radius: 4px;
  line-height: 3;
}
</style>
