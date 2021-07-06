<template>
	<div class="boards-container">
		<div class="boards-section">
			<h2 class="section-title">Personal Boards</h2>
			<div class="boards">
				<div
					id="boardList"
					class="boards__list"
					v-for="board in personalBoards"
					v-bind:key="board.id"
				>
					<h3>{{ board.name }}</h3>
					<p>{{ board.description }}</p>
				</div>
				<div class="boards__add">
					<font-awesome-icon icon="plus" @click="createBoard(0)" />
					<div>Create New Board</div>
				</div>
			</div>
		</div>
		<div class="boards-section" v-for="team in teamBoards" v-bind:key="team.id">
			<h2 class="section-title">{{ team.name }}</h2>
			<div class="boards">
				<div
					class="boards__list"
					v-for="board in team.boards"
					v-bind:key="board.id"
				>
					<h3>{{ board.name }}</h3>
					<p>{{ board.description }}</p>
				</div>
				<div class="boards__add">
					<font-awesome-icon icon="plus" @click="createBoard(team.id)" />
					<div>Create New Board</div>
				</div>
			</div>
		</div>

		<div class="team">
			<button class="team-add__btn" @click="createTeam()">
				+ Create New Team
			</button>
		</div>

		<CreateBoardModal
			v-if="showBoardModal"
			@close="showBoardModal = false"
			@created="createdBoard"
			:selectedTeamId="this.selectedTeamId"
		>
		</CreateBoardModal>
		<CreateTeamModal
			v-if="showTeamModal"
			@close="showTeamModal = false"
			@created="createdTeam"
		>
		</CreateTeamModal>
	</div>
</template>

<script>
import { getMyTeamAndBoard } from '@/api/me';
import CreateBoardModal from '@/components/modal/CreateBoardModal.vue';
import CreateTeamModal from '@/components/modal/CreateTeamModal.vue';

export default {
	data() {
		return {
			showBoardModal: false,
			showTeamModal: false,
			selectedTeamId: 0,
		};
	},
	components: {
		CreateBoardModal,
		CreateTeamModal,
	},
	computed: {
		personalBoards() {
			console.log('11');
			return this.$store.getters.getPersonalBoards;
		},
		teamBoards() {
			return this.$store.getters.getTeamBoards;
		},
	},
	methods: {
		createBoard(teamId) {
			if (teamId !== 0) {
				this.selectedTeamId = teamId;
			}
			this.showBoardModal = true;
		},
		createdBoard() {
			this.showBoardModal = false;
		},
		createTeam() {
			this.showTeamModal = true;
		},
		createdTeam() {
			this.showTeamModal = false;
		},
	},
	async created() {
		const { data } = await getMyTeamAndBoard();
		this.$store.commit('setUsername', data.user.name);
		this.$store.commit('setBoards', data.boards);
		this.$store.commit('setTeams', data.teams);
	},
};
</script>

<style scoped>
.boards-container {
	padding: 0 35px;
}

.boards-container h2 {
	font-size: 18px;
	margin-bottom: 15px;
	font-weight: bold;
}

.boards-section {
	margin: 30px 10px;
}

.boards {
	display: flex;
	flex-wrap: wrap;
}

.boards__list {
	width: 270px;
	height: 110px;
	border-radius: 5px;
	background-color: #42b983;
	color: #fff;
	padding: 15px;
	margin-right: 10px;
	margin-bottom: 10px;
	cursor: pointer;
}

.boards__list h3 {
	font-size: 16px;
	margin: 10px;
}

.boards__list p {
	margin: 10px;
	line-height: 1.2;
	font-size: 90%;
	font-weight: 100;
	color: rgba(255, 255, 255, 0.7);
}

.boards__add {
	width: 270px;
	height: 110px;
	border-radius: 5px;
	background-color: #f4f4f4;
	color: #666;
	text-align: center;
	padding-top: 30px;
	cursor: pointer;
	font-weight: 400;
}

#boardList {
	margin-bottom: 10px;
}

.team-add__btn {
	color: #666;
	cursor: pointer;
	text-decoration: underline;
	margin-bottom: 5;
	padding: 15px;
}
</style>
