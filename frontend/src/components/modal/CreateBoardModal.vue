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
			console.log(data);
			this.$store.commit('addBoard', data);
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

<style>
.modal-mask {
	position: fixed;
	z-index: 9998;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: table;
	transition: opacity 0.3s ease;
}

.modal-wrapper {
	display: table-cell;
	vertical-align: middle;
}

.modal-container {
	width: 50%;
	height: 20%;
	margin: 0px auto;
	padding: 20px 30px;
	background-color: #fff;
	border-radius: 2px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
	transition: all 0.3s ease;
	font-family: Helvetica, Arial, sans-serif;
}

.modal-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.modal-header h3 {
	color: #42b983;
}

.close {
	font-size: 30px;
}

.modal-body {
	margin: 20px 0;
}

.modal-footer {
	height: 50%;
}

.modal-default-button {
	float: right;
}

.modal-enter {
	opacity: 0;
}

.modal-leave-active {
	opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
	-webkit-transform: scale(1.1);
	transform: scale(1.1);
}
</style>
