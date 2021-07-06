<template>
	<div>
		<AppHeader></AppHeader>
		<BoardForm></BoardForm>
		<AppFooter></AppFooter>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import AppFooter from '@/components/common/AppFooter.vue';
import BoardForm from '@/components/BoardListItem.vue';

export default {
	components: {
		AppHeader,
		BoardForm,
		AppFooter,
	},
};
</script>

<style scoped></style>
