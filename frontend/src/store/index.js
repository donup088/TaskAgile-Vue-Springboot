import Vue from 'vue';
import Vuex from 'vuex';
import { loginUser } from '@/api/auth';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		username: '',
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
			localStorage.setItem('accessToken', data.accessToken);
			commit('setUsername', data.name);
			return data;
		},
		OAUTHLOGIN(token) {
			localStorage.setItem('accessToken', token);
		},
	},
});
