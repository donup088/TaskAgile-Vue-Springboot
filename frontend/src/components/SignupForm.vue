<template>
	<form class="registerForm" @submit.prevent="submitForm">
		<v-text-field v-model="username" label="Username" required></v-text-field>
		<p class="validation-text">
			<span class="warning" v-if="!isUsernameValid && username">
				이름을 2글자 이상 입력해주세요.
			</span>
		</p>
		<v-text-field
			v-model="email"
			label="Email"
			required
			type="email"
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
		<p class="validation-text">
			<span class="warning" v-if="!isPasswordValid && password">
				비밀번호를 4자리 이상 입력해주세요.
			</span>
		</p>
		<v-btn
			:disabled="!isUsernameValid || !isEmailValid || !isPasswordValid"
			class="registerBtn"
			type="submit"
			>회원가입</v-btn
		>
		<p class="pre-signup">
			회원가입을 이미 하셨나요? 배포 자동화 테스트
			<a href="/login">로그인하러가기</a>
		</p>
	</form>
</template>

<script>
import { registerUser } from '@/api/auth';
import {
	validateEmail,
	validateUsername,
	validatePassword,
} from '@/utils/validation';

export default {
	data() {
		return {
			username: '',
			email: '',
			password: '',
		};
	},
	computed: {
		isEmailValid() {
			return validateEmail(this.email);
		},
		isUsernameValid() {
			return validateUsername(this.username);
		},
		isPasswordValid() {
			return validatePassword(this.password);
		},
	},
	methods: {
		async submitForm() {
			try {
				const userData = {
					name: this.username,
					email: this.email,
					password: this.password,
				};
				await registerUser(userData);
			} catch (error) {
				console.log(error);
			}
			this.initForm();
			this.$router.push('/login');
		},
		kakaoLogin() {
			console.log('kakaologin');
		},
		initForm() {
			this.username = '';
			this.password = '';
			this.email = '';
		},
	},
};
</script>

<style scoped>
.registerForm {
	width: 50%;
}

.registerBtn {
	width: 100%;
	margin-top: 35px;
}

.pre-signup {
	opacity: 0.7;
	margin-top: 20px;
	text-align: center;
}

.pre-signup > a {
	font-weight: bold;
}
</style>
