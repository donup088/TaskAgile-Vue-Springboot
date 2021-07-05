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
		teams: [],
		boards: [],
	},
	getters: {
		isLogin(state) {
			return state.username !== '';
		},
		getPersonalBoards(state) {
			return state.boards.filter(board => board.teamId === 0);
		},
		getTeamBoards(state) {
			const teams = [];
			state.teams.forEach(team => {
				teams.push({
					id: team.id,
					name: team.name,
					boards: state.boards.filter(board => board.teamId === team.id),
				});
			});
			return teams;
		},
	},
	mutations: {
		setToken(state, token) {
			state.token = token;
		},
		clearToken(state) {
			state.token = '';
		},
		setUsername(state, username) {
			state.username = username;
		},
		setBoards(state, boards) {
			state.boards = boards;
		},
		setTeams(state, teams) {
			state.teams = teams;
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
