import { refreshToken } from '@/api/auth';
import axios from 'axios';

import store from '@/store/index';

export function setInterceptors(instance) {
	// Add a request interceptor
	instance.interceptors.request.use(
		function (config) {
			config.headers.Authorization =
				'Bearer ' + localStorage.getItem('accessToken');
			return config;
		},
		function (error) {
			// Do something with request error
			return Promise.reject(error);
		},
	);

	// Add a response interceptor
	instance.interceptors.response.use(
		response => {
			return response;
		},
		async error => {
			const {
				config,
				response: { status },
			} = error;
			if (status === 401) {
				const userRefreshReq = {
					userId: store.getters['user/getUserId'],
				};

				const { data } = await refreshToken(userRefreshReq);

				localStorage.setItem('accessToken', data.accessToken);
				error.response.config.headers.Authorization =
					'Bearer ' + data.accessToken;
				return axios(config);
			}

			return Promise.reject(error);
		},
	);

	return instance;
}
