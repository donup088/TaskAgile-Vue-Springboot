<template>
	<footer class="footer">
		<span class="copyright">&copy; 2021 TaskAgile.com</span>
		<ul class="footer-links float-right">
			<li class="list-inline-item">
				<a href="#">About</a>
			</li>
			<li class="list-inline-item">
				<a href="#">Terms of Service</a>
			</li>
			<li class="list-inline-item">
				<a href="#">Privacy Policy</a>
			</li>
			<li class="list-inline-item">
				<a
					href="https://github.com/donup3/TaskAgile-Vue-Springboot"
					target="_blank"
					>GitHub</a
				>
			</li>
		</ul>
	</footer>
</template>

<script>
export default {};
</script>

<style scoped>
.footer {
	position: absolute;
	bottom: 10px;
	width: 100%;
	display: flex;
	font-size: 18px;
	color: #666;
	line-height: 40px;
	border-top: 1px solid #ddd;
	margin-top: 50px;
	justify-content: space-between;
}

.footer > a {
	color: #666;
}

.footer > .copyright {
	margin-left: 8px;
}

.list-inline-item {
	margin: 0px 10px;
	padding: 0px 10px;
}
.footer-links {
	display: flex;
}
</style>
