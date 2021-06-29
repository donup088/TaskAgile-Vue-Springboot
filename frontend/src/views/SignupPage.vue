<template>
	<div class="container">
		<Logo></Logo>
		<form class="registerForm">
			<v-text-field v-model="name" label="Username" required></v-text-field>
			<v-text-field v-model="email" label="Email" required></v-text-field>
			<v-text-field v-model="Password" label="Password" required></v-text-field>
			<v-btn class="registerBtn" @click="submit">회원가입</v-btn>
			<p class="pre-signup">
				회원가입을 이미 하셨나요?
				<a href="/login">로그인하러가기</a>
			</p>
		</form>
	</div>
</template>

<script>
import Logo from '@/components/Logo.vue';

export default {
	data() {
		return {
			name: '',
			email: '',
			password: '',
		};
	},
	components: {
		Logo,
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
}
.registerForm {
	width: 70%;
}
.registerBtn {
	width: 100%;
	margin-top: 35px;
}

.pre-signup,
.findPassword {
	opacity: 0.7;
	margin-top: 20px;
	text-align: center;
}
</style>
