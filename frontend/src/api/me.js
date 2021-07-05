import { instanceWithAuth } from './index';

function getMyTeamAndBoard() {
	return instanceWithAuth.get('/me/board');
}

export { getMyTeamAndBoard };
