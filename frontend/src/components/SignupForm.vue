<template>
	<form class="registerForm">
		<v-text-field v-model="username" label="Username" required></v-text-field>
		<p class="validation-text">
			<span class="warning" v-if="isUsernameValid"> 이름을 입력해주세요. </span>
		</p>
		<v-text-field v-model="email" label="Email" required></v-text-field>
		<p class="validation-text">
			<span class="warning" v-if="!isEmailValid"> 이메일을 입력해주세요 </span>
		</p>
		<v-text-field
			v-model="password"
			label="Password"
			required
			type="password"
		></v-text-field>
		<p class="validation-text">
			<span class="warning" v-if="isPasswordValid">
				비밀번호를 입력해주세요.
			</span>
		</p>
		<v-btn class="registerBtn" @click="submitForm">회원가입</v-btn>
		<p class="pre-signup">
			회원가입을 이미 하셨나요?
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
			const userData = {
				name: this.username,
				email: this.email,
				password: this.password,
			};
			await registerUser(userData);
			this.initForm();
			this.$router.push('/login');
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

.pre-signup,
.findPassword {
	opacity: 0.7;
	margin-top: 20px;
	text-align: center;
}
</style>
