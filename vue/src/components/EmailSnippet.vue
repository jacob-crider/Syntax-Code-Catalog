<template>
  <form ref="form" @submit.prevent="sendEmail">
    <label>Sender Name</label>
    <input type="text" name="sender_name" v-model="sender_name">
     <label>Sender Email</label>
    <input type="email" name="sender_email" v-model="sender_email">
    <label>Receiver Name</label>
    <input type="text" name="receiver_name" v-model="receiver_name">
    <label>Receiver Email</label>
    <input type="email" name="receiver_email" v-model="receiver_email">
    <textarea name="message" id="" cols="30" rows="10" v-model="message" v-show="displayMessage"></textarea>
    <input type="submit" value="Send">
  </form>
</template>

<script>
import emailjs from "emailjs-com";

export default {
  name: "emailsnippet",
  data() {
    return {    
      receiver_email: '',
      sender_name: '',
      sender_email: '',
      receiver_name: '',
      message: `${this.example.title} \n ${this.example.languageType} \n ${this.example.snippet}`,
      displayMessage: false
    }
  },
  methods: {
    sendEmail() {
      emailjs.sendForm('service_ntt3qtv', 'template_kiux2ir', this.$refs.form, 'user_SGnUIPQ7CqPCd1wMp8ZWV')
        .then((result) => {
          console.log(result.status)
            alert('SUCCESS!');
        }, (error) => {
          console.log(error.status)
            alert('FAILED...');
        });
    }
  },
  props: ['example']
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.container {
  display: block;
  margin: auto;
  text-align: center;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  width: 50%;
}

label {
  float: left;
}

input[type="text"],
[type="email"],
textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type="submit"] {
  background-color: #4caf50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: #45a049;
}
</style>