<template>
  <div>
      <input type="text" v-model="comment.description">
      <button @click.prevent="addComment">Add Comment</button>
      <comment v-for="currentComment in comments" v-bind:comment="currentComment" v-bind:key="currentComment.id"/>
  </div>
</template>

<script>
import Comment from './Comment.vue'
import exampleService from '../services/ExampleService'

export default {
    name: 'CommentList',
    components: {
        Comment
    },
    props: ['example'],
    data() {
        return {
            comments: [],
            comment: {
            exampleId: this.example.exampleId,
            description: ''
            }
        }
    },
    created() {
        this.comments = this.example.comments;
    },
    methods: {
        addComment() {
            exampleService.addComment(this.comment).then(response => {
                if (response.status === 200) {
                    console.log("Success!");
                }
            }).catch(error => {
                console.error(error);
            })
        } 
    }
}
</script>

<style>

</style>