import { instance } from './index';

function registerUser(userData) {
	return instance.post('/auth/register', userData);
}

function loginUser(userData) {
	return instance.post('/auth/login', userData);
}

function refreshToken(userData) {
	return instance.post('/auth/refreshtoken', userData);
}

export { registerUser, loginUser, refreshToken };
