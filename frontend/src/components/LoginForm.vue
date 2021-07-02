<template>
	<form class="loginForm">
		<v-text-field
			v-model="email"
			label="Email"
			type="email"
			required
		></v-text-field>
		<p class="validation-text">
			<span class="warning" v-if="!isEmailValid && email">
				이메일 형식에 맞춰서 입력해주세요.
			</span>
		</p>
		<v-text-field
			v-model="password"
			label="Password"
			required
			type="password"
		></v-text-field>
		<v-btn
			:disabled="!isEmailValid || !isPasswordValid"
			class="loginBtn"
			@click="submitForm"
			>로그인</v-btn
		>
		<p class="pre-signup">
			회원가입을 안하셨나요?
			<a href="/signup">회원가입하러가기</a>
		</p>
		<p class="findPassword"><a href="#">비밀번호를 잊어버리셨나요?</a></p>
	</form>
</template>

<script>
import { validateEmail, validatePassword } from '@/utils/validation';

export default {
	data() {
		return {
			email: '',
			password: '',
		};
	},
	computed: {
		isEmailValid() {
			return validateEmail(this.email);
		},
		isPasswordValid() {
			return validatePassword(this.password);
		},
	},
	methods: {
		async submitForm() {
			try {
				const userData = {
					email: this.email,
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
			this.email = '';
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
.pre-signup > a {
	font-weight: bold;
}
</style>
