import { instance } from './index';

function registerUser(userData) {
	return instance.post('/user/register', userData);
}

function loginUser(userData) {
	return instance.post('/user/login', userData);
}

export { registerUser, loginUser };
