import { instanceWithAuth } from './index';

function createBoard(boardData) {
	return instanceWithAuth.post('/board', boardData);
}

function getBoard(boardId) {
	return instanceWithAuth.get(`/board/${boardId}`);
}

export { createBoard, getBoard };
