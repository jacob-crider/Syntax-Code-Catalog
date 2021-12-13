<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }"><img id="logo-image" src="SyntaxWithColors.png" alt="Logo"></router-link>
      <div class="menu">
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
  color: #fafafa ;
}

*, *:before, *:after {
  box-sizing: inherit;
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

#logo-image {
  width: 300px;
  height: auto;
  padding: 10px;
}

#nav {
  background-color: #2C2925 ;
  width: 100%;
}

div.menu{
  float: right;
}
</style>
