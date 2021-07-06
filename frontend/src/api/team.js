import { instanceWithAuth } from './index';

function createTeam(teamData) {
	return instanceWithAuth.post('/team', teamData);
}

export { createTeam };
