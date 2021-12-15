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
        <a class="menu-item" @click.prevent="toggleTheme">Light</a>
      </div>
    </div>
    <div id="spacer" />
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
  methods: {
    toggleTheme(event) {
      if (event.target.textContent === 'Light') {
        event.target.textContent ='Dark';
        document.querySelector('#prism').media = 'none';
        document.querySelector('#prism-okaidia').media = '';
        document.querySelector('html').classList.add('dark');
      } else {
        event.target.textContent ='Light';
        document.querySelector('#prism').media = '';
        document.querySelector('#prism-okaidia').media = 'none';
        document.querySelector('html').classList.remove('dark');
      }
    }
  }
};
</script>

<style>
:root {
  --rich-black-fogra-29: #0d1b2a;
  --oxford-blue: #1b263b;
  --bdazzled-blue: #415a77;
  --shadow-blue: #778da9;
  --platinum: #e0e1dd;
  --violet: #e303ff;

  --bg-color: var(--platinum);
  --card-color: var(--bdazzled-blue);
  --form-color: var(--bdazzled-blue);
  --navbar-color: var(--bdazzled-blue);
  --button-color: var(--shadow-blue);
  --input-color: var(--platinum);
  --text-color: var(--platinum);
  --link-color: var(--rich-black-fogra-29);
  --accent: var(--violet);
}

.dark {
  --bg-color: var(--rich-black-fogra-29);
  --link-color: var(--platinum);
}

html {
  font-family: "Montserrat", sans-serif;
  box-sizing: border-box;
  background-color: var(--bg-color);
  color: var(--text-color);
}

*, *:before, *:after {
  box-sizing: inherit;
}

body {
  margin: 0;
}

h1, h2 {
  color: var(--accent);
  text-shadow: 1px 1px 2px black;
  font-weight: normal;
  text-align: center;
}

a {
  color: var(--link-color);
  text-decoration: none;
}

a:hover {
  color: var(--accent);
  text-decoration: underline;
}

button {
  padding: 8px;
  background-color: var(--button-color);
  color: var(--text-color);
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
  background-color: var(--input-color);
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
  background-color: var(--bg-color);
  width: 100%;
}

.menu {
  float: right;
}

.menu-item {
  padding: 0 16px;
  line-height: 3;
}
</style>
