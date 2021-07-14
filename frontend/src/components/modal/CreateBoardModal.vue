<template>
	<form @submit.prevent="saveBoard">
		<div class="modal-mask">
			<div class="modal-wrapper">
				<div class="modal-container">
					<div class="modal-header">
						<h3 class="modal-title">보드 만들기</h3>
						<font-awesome-icon
							icon="times"
							class="times-circle"
							@click="close"
						/>
					</div>

					<div class="modal-body">
						<v-text-field
							autofocus
							class="modal-body__boardName"
							v-model="board.name"
							placeholder="Board의 이름을 입력해주세요"
							type="text"
						></v-text-field>
						<v-text-field
							v-model="board.description"
							placeholder="Board의 설명을 적어주세요"
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
import { createBoard } from '@/api/board';

export default {
	props: ['selectedTeamId'],
	data() {
		return {
			board: {
				name: '',
				description: '',
			},
		};
	},
	methods: {
		async saveBoard() {
			const boardData = {
				name: this.board.name,
				description: this.board.description,
				teamId: this.selectedTeamId,
			};
			const { data } = await createBoard(boardData);
			this.$store.commit('board/addBoard', data);
			this.$emit('created');
		},
		close() {
			this.board.name = '';
			this.board.description = '';
			this.$emit('close');
		},
	},
};
</script>

<style scoped></style>
