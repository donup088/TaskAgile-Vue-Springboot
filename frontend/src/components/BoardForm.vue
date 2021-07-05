<template>
	<div class="boards-container">
		<div class="boards-section">
			<h2 class="section-title">Personal Boards</h2>
			<div class="boards">
				<div id="boardList" class="boards__list">
					<h3>테스트보드1</h3>
					<p>테스트보드 내용 1</p>
				</div>
				<div id="boardList" class="boards__list">
					<h3>테스트보드2</h3>
					<p>테스트보드 내용 2</p>
				</div>
				<div class="boards__add">
					<font-awesome-icon icon="plus" />
					<div>Create New Board</div>
				</div>
			</div>
		</div>
		<div class="boards-section">
			<h2 class="section-title">테스트 팀2</h2>
			<div class="boards">
				<div class="boards__list">
					<h3>테스트보드</h3>
					<p>테스트보드는 이렇다</p>
				</div>
				<div class="boards__add">
					<font-awesome-icon icon="plus" />
					<div>Create New Board</div>
				</div>
			</div>
		</div>

		<div class="team">
			<button class="team-add__btn">+ Create New Team</button>
		</div>
	</div>
</template>

<script>
export default {};
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
}

.boards__list {
	width: 270px;
	height: 110px;
	border-radius: 5px;
	background-color: #377ef6;
	color: #fff;
	padding: 15px;
	margin-right: 10px;
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
	font-weight: 400;
}

#boardList {
	margin-bottom: 10px;
}

.team-add__btn {
	color: #666;
	text-decoration: underline;
	padding: 15px;
}
</style>
