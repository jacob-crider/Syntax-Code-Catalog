<template>
  <div>
    <button @click.prevent="addComment">Add Comment</button>
    <input type="text" v-model="comment.description" />
    <div v-if="comments !== []">
      <comment
        v-for="currentComment in comments"
        v-bind:comment="currentComment"
        v-bind:key="currentComment.id"
      />
    </div>
  </div>
</template>

<script>
import Comment from "./Comment.vue";
import exampleService from "../services/ExampleService";

export default {
  name: "CommentList",
  components: {
    Comment,
  },
  props: ["example"],
  data() {
    return {
      comments: [],
      comment: {
        exampleId: this.example.exampleID,
        description: "",
      },
    };
  },
  created() {
    this.comments = this.example.comments.slice();
  },
  methods: {
    addComment() {
      exampleService
        .addComment(this.comment)
        .then((response) => {
          if (response.status === 200) {
            console.log("Success!");
            this.comment.description = '';
            this.$router.go(this.$router.currentRoute);
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style>
</style>