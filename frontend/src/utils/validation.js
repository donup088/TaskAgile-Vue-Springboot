function validateEmail(email) {
	const re =
		/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(String(email).toLowerCase()) || email.length < 0;
}

function validateUsername(usernmae) {
	return usernmae.length > 1;
}

function validatePassword(password) {
	return password.length > 3;
}

export { validateEmail, validateUsername, validatePassword };
