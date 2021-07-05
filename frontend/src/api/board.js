import { instanceWithAuth } from './index';

function createBoard(boardData) {
	return instanceWithAuth.post('/board', boardData);
}

export { createBoard };
