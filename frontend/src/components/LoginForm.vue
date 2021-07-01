<template>
	<form class="loginForm">
		<v-text-field v-model="username" label="Email" required></v-text-field>
		<p class="validation-text">
			<span class="warning" v-if="!isUsernameValid && username">
				Please enter an email address
			</span>
		</p>
		<v-text-field
			v-model="password"
			label="Password"
			required
			type="password"
		></v-text-field>
		<v-btn class="loginBtn" @click="submitForm">로그인</v-btn>
		<p class="pre-signup">
			회원가입을 안하셨나요?
			<a href="/signup">회원가입하러가기</a>
		</p>
		<p class="findPassword"><a href="#">비밀번호를 잊어버리셨나요?</a></p>
	</form>
</template>

<script>
import { validateEmail } from '@/utils/validation';

export default {
	data() {
		return {
			username: '',
			password: '',
		};
	},
	computed: {
		isUsernameValid() {
			return validateEmail(this.username);
		},
	},
	methods: {
		async submitForm() {
			try {
				const userData = {
					email: this.username,
					password: this.password,
				};
				await this.$store.dispatch('LOGIN', userData);
				this.$router.push('/main');
			} catch (error) {
				console.log(error);
			} finally {
				this.initForm();
			}
		},
		initForm() {
			this.username = '';
			this.password = '';
		},
	},
};
</script>

<style scoped>
.loginForm {
	width: 50%;
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
