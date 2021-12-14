<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
        <div class="login">
          <div class="form-inputs">
            <label for="username" class="sr-only">Username</label>
            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            />
          </div>
          <div class="form-inputs">
            <label for="password" class="sr-only">Password</label>
            <input
              type="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="user.password"
              required
            />
          </div>

          <div class="register-link">
            <router-link :to="{ name: 'register' }"
              >Need an account?</router-link>
          </div>
          <div class="submit-form">
            <button type="submit">Sign in</button>
          </div>
        </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
  color: #BC80EC;
}

div.login {
  width: 480px;
  background-color: #5450D8;
  margin: auto;
  text-align: center;
  border-radius: 4px;
  padding: 32px;
}

div.form-inputs {
  margin: 32px;
  line-height: 1.5;
}

div.submit-form {
  margin: 16px;
}

label {
  padding-right: 4px;
}
</style>
