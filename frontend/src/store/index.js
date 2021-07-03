import Vue from 'vue';
import Vuex from 'vuex';
import {
	getAuthFromCookie,
	getUserFromCookie,
	saveAuthToCookie,
} from '@/utils/cookies';
import { loginUser } from '@/api/auth';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		username: getUserFromCookie() || '',
		token: getAuthFromCookie() || '',
	},
	getters: {
		isLogin(state) {
			return state.username !== '';
		},
	},
	mutations: {
		setToken(state, token) {
			state.token = token;
		},
		clearToken(state) {
			state.token = '';
		},
	},
	actions: {
		async LOGIN({ commit }, userData) {
			const { data } = await loginUser(userData);
			commit('setToken', data.accessToken);
			saveAuthToCookie(data.accessToken);
			return data;
		},
		OAUTHLOGIN({ commit }, token) {
			commit('setToken', token);
			saveAuthToCookie(token);
		},
	},
});
