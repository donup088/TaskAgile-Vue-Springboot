<template>
	<div class="app">
		<router-view></router-view>
		<AppFooter></AppFooter>
	</div>
</template>

<script>
import AppFooter from '@/components/common/AppFooter.vue';

export default {
	created() {
		this.checkUrl();
	},
	components: {
		AppFooter,
	},
	methods: {
		checkUrl() {
			const urlToken = location.href.split('token=')[1];
			if (urlToken) {
				localStorage.setItem('accessToken', urlToken);
				location.href = '/main';
			}
		},
	},
};
</script>

<style>
@import './css/reset.css';
@import './css/common.css';

.app {
	display: flex;
	flex-direction: column;
	position: relative;
	height: 100%;
}
</style>
