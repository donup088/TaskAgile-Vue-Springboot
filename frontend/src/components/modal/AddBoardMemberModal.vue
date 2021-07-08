<template>
	<form @submit.prevent="addMember">
		<div class="modal-mask">
			<div class="modal-wrapper">
				<div class="modal-container">
					<div class="modal-header">
						<h3 class="modal-title">멤버 추가하기</h3>
						<font-awesome-icon
							icon="times"
							class="times-circle"
							@click="close"
						/>
					</div>

					<div class="modal-body">
						<v-text-field
							v-model="username"
							autofocus
							placeholder="추가할 멤버의  이름을 입력해주세요"
							type="text"
						></v-text-field>
					</div>

					<div class="modal-footer">
						<slot name="footer">
							<button type="submit" class="modal-default-button">
								추가하기
							</button>
						</slot>
					</div>
				</div>
			</div>
		</div>
	</form>
</template>

<script>
import { addMember } from '@/api/board';

export default {
	data() {
		return {
			username: '',
		};
	},

	methods: {
		async addMember() {
			const memberData = {
				name: this.username,
			};
			const { data } = await addMember(this.$route.params.id, memberData);
			console.log(data);

			this.$emit('addMember', data);
		},
		close() {
			this.username = '';
			this.$emit('close');
		},
	},
};
</script>

<style scoped></style>
