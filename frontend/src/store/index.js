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
			return state.token !== '';
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
		clearUsername(state) {
			state.username = '';
		},
		setBoards(state, boards) {
			state.boards = boards;
		},
		addBoard(state, board) {
			state.boards.push(board);
		},
		setTeams(state, teams) {
			state.teams = teams;
		},
		addTeam(state, team) {
			state.teams.push(team);
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
