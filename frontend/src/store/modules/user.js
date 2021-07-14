import { loginUser } from '@/api/auth';

export default {
	namespaced: true,
	state: {
		username: '',
	},
	getters: {
		isLogin() {
			return localStorage.getItem('access_token') !== '';
		},
		getUsername(state) {
			return state.username;
		},
	},
	mutations: {
		setUsername(state, username) {
			state.username = username;
		},
		clearUsername(state) {
			state.username = '';
		},
	},
	actions: {
		async LOGIN({ commit }, userData) {
			const { data } = await loginUser(userData);
			localStorage.setItem('accessToken', data.accessToken);
			console.log(data);
			commit('setUsername', data.name);
			return data;
		},
		OAUTHLOGIN(token) {
			localStorage.setItem('accessToken', token);
		},
	},
};
