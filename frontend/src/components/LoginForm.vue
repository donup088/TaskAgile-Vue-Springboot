<template>
	<form class="loginForm" @submit.prevent="submitForm">
		<v-text-field
			v-model="email"
			label="Email"
			type="email"
			required
		></v-text-field>
		<p class="validation-text">
			<span class="warning" v-if="isNotEmpty"> 아이디를 입력해주세요. </span>
		</p>
		<v-text-field
			v-model="password"
			label="Password"
			required
			type="password"
		></v-text-field>
		<p class="validation-text">
			<span class="warning" v-if="errorMessage && !isNotEmpty">
				가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.
			</span>
		</p>
		<v-btn class="loginBtn" type="submit">로그인</v-btn>
		<v-btn class="kakao_loginBtn" @click="kakaoLogin">카카오톡 로그인</v-btn>
		<v-btn class="google_loginBtn" @click="googleLogin">구글 로그인</v-btn>
		<p class="pre-signup">
			회원가입을 안하셨나요? 배포 자동화 테스트~
			<a href="/signup">회원가입하러가기</a>
		</p>
		<p class="findPassword"><a href="#">비밀번호를 잊어버리셨나요?</a></p>
	</form>
</template>
<script>
export default {
	data() {
		return {
			email: '',
			password: '',
			errorMessage: '',
		};
	},
	computed: {
		isNotEmpty() {
			return this.errorMessage == 'NotEmpty';
		},
	},
	methods: {
		async submitForm() {
			try {
				const userData = {
					email: this.email,
					password: this.password,
				};
				await this.$store.dispatch('user/LOGIN', userData);

				this.$router.push('/main');
			} catch (error) {
				const errResponse = error.response.data;
				if (errResponse.errors) {
					this.errorMessage = error.response.data.errors[0].code;
				} else {
					this.errorMessage = errResponse.message;
				}
			} finally {
				this.initForm();
			}
		},
		initForm() {
			this.email = '';
			this.password = '';
		},
		kakaoLogin() {
			window.location.href = `${process.env.VUE_APP_URL}/oauth2/authorization/kakao`;
		},
		googleLogin() {
			window.location.href = `${process.env.VUE_APP_URL}/oauth2/authorization/google`;
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

.kakao_loginBtn {
	width: 100%;
	margin-top: 15px;
	background-color: rgb(241, 241, 16) !important;
}

.google_loginBtn {
	width: 100%;
	margin-top: 15px;
	background-color: rgb(94, 143, 216) !important;
}
</style>
