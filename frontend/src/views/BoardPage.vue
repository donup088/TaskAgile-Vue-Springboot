<template>
	<div class="page">
		<AppHeader></AppHeader>
		<div class="page-body">
			<div class="board-wrapper">
				<div class="board">
					<div class="board-header">
						<div class="board-name board-header-item">{{ board.name }}</div>
						<div class="board-header-divider"></div>
						<div class="team-name board-header-item">
							<span v-if="!board.personal">{{ team.name }}</span>
							<span v-if="board.personal">Personal</span>
						</div>
						<div class="board-header-divider"></div>
						<div class="board-members board-header-item">
							<div class="member" v-for="member in members" :key="member.id">
								<span>{{ member.name }}</span>
							</div>
							<div class="member add-member-toggle" @click="openAddMember()">
								<span><font-awesome-icon icon="user-plus" /></span>
							</div>
						</div>
					</div>
					<div class="board-body">
						<div class="list-container">
							>
							<div
								class="list-wrapper"
								v-for="cardList in cardLists"
								:key="cardList.id"
							>
								<div class="list">
									<div class="list-header">{{ cardList.name }}</div>
									<draggable
										class="cards"
										v-model="cardList.cards"
										tag="ul"
										:move="checkMove"
										@start="drag = true"
										@end="drag = false"
										:options="{
											group: 'cards',
											ghostClass: 'ghost',
											animation: 200,
											disabled: false,
										}"
									>
										<transition-group
											type="transition"
											:name="!drag ? 'flip-list' : null"
										>
											<li
												class="card-title"
												v-for="card in cardList.cards"
												:key="card.id"
											>
												{{ card.title }}
											</li>
										</transition-group>
									</draggable>
									<div
										class="add-card-form-wrapper"
										v-if="cardList.cardForm.open"
									>
										<form class="add-card-form">
											<div class="form-group">
												<textarea
													class="form-control"
													v-model="cardList.cardForm.title"
													placeholder="Type card title here"
												></textarea>
											</div>
											<v-btn
												depressed
												class="card-add__btn"
												@click="addCard(cardList)"
											>
												Add
											</v-btn>
											<v-btn
												depressed
												class="card-cancel__btn"
												@click="closeAddCardForm(cardList)"
											>
												Cancel
											</v-btn>
										</form>
									</div>
									<div
										class="add-card-button"
										v-show="!cardList.cardForm.open"
										@click="openAddCardForm(cardList)"
									>
										+ Add a card
									</div>
								</div>
							</div>
							<div class="list-wrapper add-list">
								<div
									class="add-list-button"
									v-show="!addCardListForm.open"
									@click="openAddCardListForm()"
								>
									+ Add a list
								</div>
								<form class="add-list-form" v-show="addCardListForm.open">
									<div class="form-group">
										<input
											type="text"
											class="form-control"
											id="cardListName"
											v-model="addCardListForm.name"
											placeholder="Type list name here"
										/>
									</div>
									<v-btn depressed class="card-add__btn" @click="addCardList()">
										AddList
									</v-btn>
									<v-btn
										depressed
										class="card-cancel__btn"
										@click="closeAddCardListForm()"
									>
										Cancel
									</v-btn>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<AddBoardMemberModal
			v-if="showAddMemberModal"
			@close="showAddMemberModal = false"
			@addMember="addedMember"
		>
		</AddBoardMemberModal>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import draggable from 'vuedraggable';
import AddBoardMemberModal from '@/components/modal/AddBoardMemberModal.vue';
import { createCardList, createCard } from '@/api/card';
import { getBoard } from '@/api/board';

export default {
	data() {
		return {
			board: { id: this.$route.params.id, name: '', personal: false },
			cardLists: [
				/* {id, name, cards, cardForm} */
			],
			team: { name: '' },
			members: [
				/* {id, name} */
			],
			addCardListForm: {
				name: '',
				open: false,
			},
			showAddMemberModal: false,
			drag: false,
		};
	},
	components: {
		AppHeader,
		draggable,
		AddBoardMemberModal,
	},
	async created() {
		const { data } = await getBoard(this.board.id);
		console.log(data);
		this.team.name = data.team ? data.team.name : '';
		this.board.personal = data.board.personal;
		this.board.name = data.board.name;
		data.members.forEach(member => {
			this.members.push({
				id: member.id,
				name: member.name,
			});
		});
		data.cardLists.sort((list1, list2) => {
			return list1.position - list2.position;
		});
		data.cardLists.forEach(cardList => {
			cardList.cards.sort((card1, card2) => {
				return card1.position - card2.position;
			});
			this.cardLists.push({
				id: cardList.id,
				name: cardList.name,
				cards: cardList.cards,
				cardForm: {
					open: false,
					title: '',
				},
			});
		});
	},
	methods: {
		openAddCardForm(cardList) {
			cardList.cardForm.open = true;
		},
		async addCard(cardList) {
			const cardData = {
				title: cardList.cardForm.title,
				position: cardList.cards.length + 1,
				cardListId: cardList.id,
			};
			const { data } = await createCard(cardData);
			cardList.cards.push({
				id: data.id,
				title: data.title,
			});
			cardList.cardForm.title = '';
			this.closeAddCardForm(cardList);
		},
		closeAddCardForm(cardList) {
			cardList.cardForm.open = false;
		},

		openAddCardListForm() {
			this.addCardListForm.open = true;
		},
		async addCardList() {
			const cardListData = {
				boardId: this.board.id,
				name: this.addCardListForm.name,
				position: this.cardLists.length + 1,
			};
			const { data } = await createCardList(cardListData);
			this.cardLists.push({
				id: data.id,
				name: data.name,
				cards: [],
				cardForm: { title: '', open: false },
			});
			this.closeAddCardListForm();
		},
		closeAddCardListForm() {
			this.addCardListForm.name = '';
			this.addCardListForm.open = false;
		},
		openAddMember() {
			this.showAddMemberModal = true;
		},
		addedMember(member) {
			this.members.push(member);
			this.showAddMemberModal = false;
		},
		checkMove: function (e) {
			console.log(e.draggedContext.element.id);
			console.log(e.relatedContext.element.id);
		},
	},
};
</script>

<style scoped>
.page {
	height: 100%;
}
.page-body {
	position: relative;
	height: 100%;
}
.board-wrapper {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}
.board {
	display: flex;
	flex-direction: column;
	height: 100%;
}
.board-header {
	flex: none;
	height: auto;
	padding: 8px 8px;
	margin-left: 10px;
}
.board-header-divider {
	float: left;
	border-left: 1px solid gray;
	height: 18px;
	margin: 8px 10px;
}
.board-header-item {
	float: left;
	height: 32px;
	line-height: 32px;
}

.board-name {
	font-size: 18px;
	line-height: 32px;
	margin-left: 8px;
}
.member {
	float: left;
	height: 30px;
	width: 30px;
	margin: 0 5px 0 -2px;
	border-radius: 50%;
	background-color: #377ef6;
}
.board-members span {
	text-align: center;
	display: block;
	color: #fff;
}
.add-member-toggle:hover {
	background-color: #666;
}
.add-member-toggle {
	margin-left: 5px;
	background-color: #eee;
	cursor: pointer;
}

.board-body {
	height: 90%;
	position: relative;
	overflow: auto;
}

.list-container {
	margin: 10px;
	position: absolute;
	top: 0;
	left: 8px;
	right: 0;
	bottom: 0;
	overflow-x: auto;
	overflow-y: hidden;
	white-space: nowrap;
}

.list-wrapper {
	display: inline-block;
	height: 100%;
	vertical-align: top;
	border-radius: 10px;
	width: 28%;
	margin: 0 10px;
}
.list {
	background: #eee;
	border-radius: 5px;
	display: flex;
	flex-direction: column;
	max-height: 98%;
	position: relative;
	overflow-y: auto;
}
.list-header {
	font-weight: bold;
	padding: 10px 8px;
	margin: 0 8px 8px;
}

.ghost {
	opacity: 0.5;
	background: #c8ebfb;
}

.card-title {
	overflow: hidden;
	background: #fff;
	padding: 8px 8px;
	margin: 0 10px 15px;
	border-radius: 4px;
	box-shadow: 0 1px 0 #ccc;
	cursor: pointer;
}
.add-card-button {
	padding: 8px 10px;
	color: #888;
	cursor: pointer;
	border-bottom-left-radius: 3px;
	border-bottom-right-radius: 3px;
}
.add-card-button:hover {
	background: #dfdfdf;
	color: #333;
}
.add-card-form-wrapper {
	padding: 0 8px 8px;
}
.add-card-form-wrapper > .form-group {
	margin-bottom: 5px;
}
.add-card-form-wrapper textarea {
	width: 100%;
	resize: none;
	padding: 0.3rem 0.5rem;
	box-shadow: none;
}
.card-add__btn {
	margin-right: 10px;
}
.list-wrapper.add-list {
	background: #f4f4f4;
	border-radius: 3px;
	box-sizing: border-box;
	height: auto;
	color: #888;
	margin-right: 8px;
}
.add-list-button:hover {
	background: #ddd;
	cursor: pointer;
	border-radius: 3px;
	color: #333;
}
.add-list-form {
	padding: 5px;
}
.add-list-form > .form-group {
	margin-bottom: 5px;
}
.add-list-form > .form-control {
	height: calc(1.8rem + 2px);
	padding: 0.375rem 0.3rem;
}
#cardListName {
	width: 100%;
	padding: 5px;
	border: 1px solid black;
	border-radius: 5px;
}
</style>
