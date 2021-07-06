<template>
	<div>
		<div class="main-container">
			<AppHeader></AppHeader>
			<BoardListItem></BoardListItem>
		</div>
		<AppFooter></AppFooter>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import AppFooter from '@/components/common/AppFooter.vue';
import BoardListItem from '@/components/BoardListItem.vue';

export default {
	components: {
		AppHeader,
		BoardListItem,
		AppFooter,
	},
};
</script>

<style scoped></style>
