<template>
<div id="background-image">
  <section id="main">
   <div class="register-container">
    <form @submit.prevent="register">
      <h2>Create Account</h2>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username:</label>
        <input
          type="text"
          id="username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div class="form-input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password:</label>
        <input
          type="password"
          id="confirmPassword"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <div class="form-input-group">
        <label for="email">Email:</label>
        <input type="text" id="email" v-model="user.email" required autofocus />
      </div>
      <button type="submit">Create Account</button>
      <p>
        <router-link class="login-link" :to="{ name: 'login' }"
          >Already have an account? Log in.</router-link
        >
      </p>
    </form>
  </div>
  </section>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        email: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
#background-image {
  background-color: #ece2ee;
  background: #f7f7f7
  url("https://pbs.twimg.com/media/FOMC59BaMAEuCh_.jpg:large");
  height: 50vh;
  padding: 20px;
}
form {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  text-align: center;
    color: #753d8b;
    background-color: #ece2ee;
  padding: 100px;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    max-width: 1000px;
    margin-bottom: 500px;
}
.login-link {
    color: #753d8b;
    background-color: #ece2ee;
}
h2 {
  margin: 0 auto;
}

#main {
  position: relative;
  background: #f7f7f7
    url("https://pbs.twimg.com/media/FOMC59BaMAEuCh_.jpg:large");
  padding: 4em 0 4em 0;
}

.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; 
}
</style>
