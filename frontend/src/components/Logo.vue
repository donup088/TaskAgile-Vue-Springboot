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
	margin-top: 15%;
	text-align: center;
	margin-bottom: 40px;
}

.logo-wrapper > .tagline {
	line-height: 180%;
	color: #666;
	font-size: 20px;
}

.logo-wrapper > .logo {
	width: 300px;
	margin: 0 auto;
}
</style>
