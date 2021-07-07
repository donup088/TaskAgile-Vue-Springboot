<template>
	<div class="main-container">
		<AppHeader></AppHeader>
		<BoardListItem></BoardListItem>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import BoardListItem from '@/components/BoardListItem.vue';

export default {
	components: {
		AppHeader,
		BoardListItem,
	},
};
</script>

<style scoped>
.main-container {
	flex-grow: 1;
}
</style>
