<template>
  <div>
      <span class="username">{{comment.username}}</span>
      <span>{{comment.description}}</span>
  </div>
</template>

<script>
export default {
    name: 'Comment',
    props: ['comment']
}
</script>

<style>
    .username {
        margin: 8px;
    }
</style>