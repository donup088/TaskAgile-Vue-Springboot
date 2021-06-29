module.exports = {
	devServer: {
		port: 3000,
		overlay: false,
		proxy: {
			'/api/*': {
				target: 'http://localhost:8080',
			},
		},
	},

	transpileDependencies: ['vuetify'],
};
