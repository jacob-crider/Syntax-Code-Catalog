<template>
  <div>
      <span>{{comment.username}}</span>
      <p>{{comment.description}}</p>
  </div>
</template>

<script>
export default {
    name: 'Comment',
    props: ['comment']
}
</script>

<style>

</style>