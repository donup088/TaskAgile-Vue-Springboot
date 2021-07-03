<template>
	<div class="app">
		<router-view></router-view>
	</div>
</template>

<script>
export default {
	created() {
		this.checkUrl();
	},
	methods: {
		checkUrl() {
			const urlToken = location.href.split('token=')[1];
			if (urlToken) {
				this.$store.dispatch('OAUTHLOGIN', urlToken);
				location.href = '/main';
			}
		},
	},
};
</script>

<style>
@import './css/reset.css';
@import './css/common.css';
</style>
