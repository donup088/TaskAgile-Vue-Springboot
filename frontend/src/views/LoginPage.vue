<template>
	<div class="contents">
		<Logo></Logo>
		<LoginForm></LoginForm>
	</div>
</template>

<script>
import Logo from '@/components/Logo.vue';
import LoginForm from '@/components/LoginForm.vue';

export default {
	components: {
		Logo,
		LoginForm,
	},
};
</script>

<style scoped>
.contents {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}
</style>
