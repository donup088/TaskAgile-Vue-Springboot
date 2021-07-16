<template>
	<form @submit.prevent="saveTeam">
		<div class="modal-mask">
			<div class="modal-wrapper">
				<div class="modal-container">
					<div class="modal-header">
						<h3 class="modal-title">팀 만들기</h3>
						<font-awesome-icon
							icon="times"
							class="times-circle"
							@click="close"
						/>
					</div>

					<div class="modal-body">
						<v-text-field
							autofocus
							v-model="team.name"
							placeholder="Team의 이름을 입력해주세요"
							type="text"
						></v-text-field>
					</div>

					<div class="modal-footer">
						<slot name="footer">
							<button type="submit" class="modal-default-button">생성</button>
						</slot>
					</div>
				</div>
			</div>
		</div>
	</form>
</template>

<script>
import { createTeam } from '@/api/team';

export default {
	data() {
		return {
			team: {
				name: '',
			},
		};
	},
	methods: {
		async saveTeam() {
			const teamData = {
				name: this.team.name,
			};
			const { data } = await createTeam(teamData);
			this.$store.commit('board/addTeam', data);
			this.$emit('created');
		},
		close() {
			this.team.name = '';
			this.$emit('close');
		},
	},
};
</script>

<style scoped></style>
