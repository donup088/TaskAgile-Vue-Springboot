export default {
	namespaced: true,
	state: {
		teams: [],
		boards: [],
	},
	getters: {
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
};
