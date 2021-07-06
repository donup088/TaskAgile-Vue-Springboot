<template>
	<div>
		<AppHeader></AppHeader>
		<div class="page">
			<div class="page-body">
				<div class="board-wrapper">
					<div class="board">
						<div class="board-header">
							<div class="board-name board-header-item">보드 이름</div>
							<div class="board-header-divider"></div>
							<div class="team-name board-header-item">
								<span>팀 이름</span>
								<span>Personal</span>
							</div>
							<div class="board-header-divider"></div>
							<div class="board-members board-header-item">
								<div class="member">
									<span>멤버 이름</span>
								</div>
								<div class="member add-member-toggle">
									<span><font-awesome-icon icon="user-plus" /></span>
								</div>
							</div>
						</div>
						<div class="board-body">
							<draggable v-model="cardLists" class="list-container">
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
											:options="{
												animation: 0,
												scrollSensitivity: 100,
												touchStartThreshold: 20,
											}"
										>
											<div
												class="card-item"
												v-for="card in cardList.cards"
												:key="card.id"
											>
												<div class="card-title">{{ card.title }}</div>
											</div>
											<div
												class="add-card-form-wrapper"
												v-if="cardList.cardForm.open"
											>
												<form class="add-card-form">
													<div class="form-group">
														<textarea
															class="form-control"
															placeholder="Type card title here"
														></textarea>
													</div>
													<v-btn depressed class="card-add__btn"> Add </v-btn>
													<v-btn
														depressed
														class="card-cancel__btn"
														@click="closeAddCardForm(cardList)"
													>
														Cancel
													</v-btn>
												</form>
											</div>
										</draggable>
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
										v-show="!addListForm.open"
										@click="openAddListForm()"
									>
										+ Add a list
									</div>
									<form class="add-list-form" v-show="addListForm.open">
										<div class="form-group">
											<input
												type="text"
												class="form-control"
												id="cardListName"
												placeholder="Type list name here"
											/>
										</div>
										<v-btn depressed class="card-add__btn"> AddList </v-btn>
										<v-btn
											depressed
											class="card-cancel__btn"
											@click="closeAddListForm()"
										>
											Cancel
										</v-btn>
									</form>
								</div>
							</draggable>
						</div>
					</div>
				</div>
			</div>
		</div>
		<AppFooter></AppFooter>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import AppFooter from '@/components/common/AppFooter.vue';
import draggable from 'vuedraggable';

export default {
	data() {
		return {
			cardLists: [
				{
					id: 1,
					name: '테스트 카드 리스트1',
					title: '테스트 카드 타이틀1',
					cards: [
						{
							id: 1,
							title: '테스트 카드 리스트1의 카드1',
						},
						{
							id: 2,
							title: '테스트 카드 리스트1의 카드2',
						},
					],
					cardForm: {
						open: false,
					},
				},
				{
					id: 2,
					name: '테스트 카드 리스트2',
					title: '테스트 카드 타이틀2',
					cards: [
						{
							id: 3,
							title: '테스트 카드 리스트2의 카드1',
						},
						{
							id: 4,
							title: '테스트 카드 리스트2의 카드2',
						},
					],
					cardForm: {
						open: false,
					},
				},
			],
			addListForm: {
				open: false,
			},
		};
	},
	components: {
		AppHeader,
		AppFooter,
		draggable,
	},
	methods: {
		openAddCardForm(cardList) {
			cardList.cardForm.open = true;
		},
		closeAddCardForm(cardList) {
			cardList.cardForm.open = false;
		},
		openAddListForm() {
			this.addListForm.open = true;
		},
		closeAddListForm() {
			this.addListForm.open = false;
		},
	},
};
</script>

<style scoped>
.page-body {
	flex-grow: 1;
	position: relative;
}

.board-wrapper {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
}

.board-header {
	flex: none;
	overflow: hidden;
	position: relative;
	padding: 8px 8px;
	margin-left: 10px;
}

.board-header-divider {
	float: left;
	border-left: 1px solid #ddd;
	height: 16px;
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
	margin: 0 0 0 -2px;
	border-radius: 50%;
	background-color: #377ef6;
	position: relative;
}

.board-members span {
	height: 30px;
	line-height: 30px;
	width: 30px;
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

.list-container {
	margin: 10px;
}

.list-wrapper {
	display: inline-block;
	height: 100%;
	vertical-align: top;
	border-radius: 10px;
	width: 28%;
	margin: 0 10px;
	background: #eee;
}

.list {
	border-radius: 5px;
	display: flex;
	flex-direction: column;
	max-height: 100%;
	position: relative;
}

.list-header {
	font-weight: bold;
	padding: 10px 8px;
	margin: 0 8px 8px;
}

.card-item {
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
