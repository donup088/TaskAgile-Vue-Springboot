<template>
	<div class="container">
		<Logo></Logo>
		<SignupForm></SignupForm>
	</div>
</template>

<script>
import Logo from '@/components/Logo.vue';
import SignupForm from '@/components/SignupForm.vue';

export default {
	components: {
		Logo,
		SignupForm,
	},
};
</script>

<style scoped>
.container {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	margin-top: 7%;
}
</style>
