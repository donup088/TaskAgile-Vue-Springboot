import Vue from 'vue';
import Vuex from 'vuex';

import user from '@/store/modules/user';
import board from '@/store/modules/board';

Vue.use(Vuex);

export default new Vuex.Store({
	modules: {
		user,
		board,
	},
});
