<template>
	<header class="page-header">
		<div class="logo">
			<font-awesome-icon icon="home" class="home-icon fa-lg" />
			<img src="@/assets/logo.png" />
		</div>
		<div class="search">
			<font-awesome-icon icon="search" class="search-icon" />
			<input type="text" placeholder="Search" class="search__input" />
		</div>
		<div class="dropdown">
			<button class="dropbtn">{{ $store.state.username }}</button>
			<div class="dropdown-content">
				<a href="#">내 정보</a>
				<a href="#">로그아웃</a>
			</div>
		</div>
	</header>
</template>

<script>
export default {};
</script>

<style scoped>
.page-header {
	margin: 10px 0px;
	width: 100%;
	padding: 10px;
	display: flex;
	align-items: center;
	justify-content: space-between;
	border-bottom: 1px solid gray;
}

.logo {
	color: #444;
	width: 33%;
	height: 30px;
	width: 115px;
	margin-top: 2px;
	cursor: pointer;
	display: flex;
}

.logo > img {
	margin-left: 5px;
	width: 115px;
	height: 25px;
}

.search {
	border: 1px solid gray;
	padding: 7px;
	border-radius: 6px;
	width: 30%;
}

.search__input {
	margin-left: 8px;
}

.dropdown {
	position: relative;
	display: inline-block;
	margin-right: 3%;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 100px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>
