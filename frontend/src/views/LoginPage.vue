<template>
	<div class="container">
		<Logo></Logo>
		<form class="loginForm">
			<v-text-field
				v-model="name"
				label="Username or Email"
				required
			></v-text-field>
			<v-text-field v-model="Password" label="Password" required></v-text-field>
			<v-btn class="loginBtn" @click="submit">로그인</v-btn>
			<p class="pre-signup">
				회원가입을 안하셨나요?
				<a href="/signup">회원가입하러가기</a>
			</p>
			<p class="findPassword"><a href="#">비밀번호를 잊어버리셨나요?</a></p>
		</form>
	</div>
</template>

<script>
import Logo from '@/components/Logo.vue';

export default {
	data() {
		return {
			name: '',
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

.loginForm {
	width: 70%;
}

.loginBtn {
	margin-top: 35px;
	width: 100%;
}

.pre-signup,
.findPassword {
	opacity: 0.7;
	margin-top: 20px;
	text-align: center;
}
</style>
