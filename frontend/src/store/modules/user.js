import { loginUser } from '@/api/auth';

export default {
	namespaced: true,
	state: {
		userId: '',
		username: '',
	},
	getters: {
		isLogin() {
			return localStorage.getItem('accessToken') !== '';
		},
		getUsername(state) {
			return state.username;
		},
		getUserId(state) {
			return state.userId;
		},
	},
	mutations: {
		setUserId(state, userId) {
			state.userId = userId;
		},
		clearUserId(state) {
			state.userId = '';
		},
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
			commit('setUserId', data.id);
			commit('setUsername', data.name);
			return data;
		},
	},
};
