<template>
	<div class="logo-wrapper">
		<img class="logo" src="@/assets/logo.png" />
		<div class="tagline">Open source task management tool</div>
	</div>
</template>

<script>
export default {};
</script>

<style scoped>
.logo-wrapper {
	text-align: center;
	margin-top: 100px;
	margin-bottom: 40px;
}

.logo-wrapper > .tagline {
	line-height: 180%;
	color: #666;
}

.logo-wrapper > .logo {
	max-width: 200px;
	margin: 0 auto;
}
</style>
