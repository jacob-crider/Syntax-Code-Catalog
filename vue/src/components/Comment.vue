<template>
  <div class="comment">
    <span class="username">{{ comment.username }}:</span>
    <span>{{ comment.description }}</span>
  </div>
</template>

<script>
export default {
  name: 'Comment',
  props: ['comment'],
};
</script>

<style>
.username {
  font-weight: bold;
  margin: 8px;
}

.comment {
  margin: 8px;
}
</style>
