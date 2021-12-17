<template>
  <div id="app">
    <div class="nav-bar">
      <router-link v-bind:to="{ name: 'home' }">
        <img id="logo-image" src="SYNTAXLight.png" alt="Logo">
      </router-link>
      <div class="menu">
        <router-link class="menu-item" v-bind:to="{ name: 'home' }" v-if="$store.state.token !== ''">
          Home
        </router-link>
        <router-link class="menu-item" v-bind:to="{ name: 'compiler' }" v-if="$store.state.token !== ''">
          Compiler
        </router-link>
        <router-link class="menu-item" v-if="isAdmin" v-bind:to="{ name: 'language' }">
          Edit Languages
        </router-link>
        <router-link class="menu-item" v-bind:to="{ name: 'logout' }" v-if="$store.state.token !== ''">
          Logout
        </router-link>
        <button id="theme-toggle" @click.prevent="toggleTheme">GO DARK</button>
      </div>
    </div>
    <div id="spacer" />
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentTheme: '',
    };
  },
  computed: {
    isAdmin() {
      return (this.$store.state.token !== '') && (this.$store.state.user.authorities[0].name === 'ROLE_ADMIN');
    },
  },
  methods: {
    toggleTheme() {
      if (this.currentTheme === 'light') {
        this.setDarkTheme();
      } else {
        this.setLightTheme();
      }
    },
    setDarkTheme() {
      document.querySelector('#theme-toggle').textContent = 'GO LIGHT';
      document.querySelector('#prism').media = 'none';
      document.querySelector('#prism-okaidia').media = '';
      document.querySelector('html').classList.add('dark');
      document.querySelector('#logo-image').setAttribute('src', 'SYNTAXDark.png');
      localStorage.setItem('current_theme', 'dark');
      this.currentTheme = 'dark';
    },
    setLightTheme() {
      document.querySelector('#theme-toggle').textContent = 'GO DARK';
      document.querySelector('#prism').media = '';
      document.querySelector('#prism-okaidia').media = 'none';
      document.querySelector('html').classList.remove('dark');
      document.querySelector('#logo-image').setAttribute('src', 'SYNTAXLight.png');
      localStorage.setItem('current_theme', 'light');
      this.currentTheme = 'light';
    },
  },
  created() {
    if (!localStorage.getItem('current_theme')) {
      localStorage.setItem('current_theme', 'light');
      this.currentTheme = 'light';
    } else {
      this.currentTheme = localStorage.getItem('current_theme');
    }
  },
  mounted() {
    if (this.currentTheme === 'light') {
      this.setLightTheme();
    } else {
      this.setDarkTheme();
    }
  },
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
  --dark-violet: #af00c5;

  --bg-color: var(--platinum);
  --card-color: var(--shadow-blue);
  --form-color: var(--shadow-blue);
  --button-color: var(--bdazzled-blue);
  --input-color: var(--platinum);
  --text-color: var(--platinum);
  --link-color: var(--rich-black-fogra-29);
  --accent: var(--oxford-blue);
}

.dark {
  --bg-color: var(--rich-black-fogra-29);
  --card-color: var(--bdazzled-blue);
  --form-color: var(--bdazzled-blue);
  --button-color: var(--oxford-blue);
  --link-color: var(--platinum);
  --accent: var(--violet);
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
  text-align: center;
}

h2 {
  font-size: 1.8rem;
  text-shadow: 1px 1px 0 black;
  font-weight: normal;
  letter-spacing: 1px;
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
}

input, select, textarea {
  border: none;
  border-radius: 4px;
  background-color: var(--input-color);
}

select {
  padding: 6px;
}

#spacer {
  margin-top: 90px;
}

#logo-image {
  height: 50px;
}

.nav-bar {
  position: fixed;
  top: 0;
  left: 0;
  padding: 16px;
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

.menu button {
  background-color: var(--card-color);
  margin: 0 8px;
}
</style>
