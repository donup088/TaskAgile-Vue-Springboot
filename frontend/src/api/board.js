import { instanceWithAuth } from './index';

function createBoard(boardData) {
	return instanceWithAuth.post('/board', boardData);
}

function getBoard(boardId) {
	return instanceWithAuth.get(`/board/${boardId}`);
}

function addMember(boardId, memberData) {
	return instanceWithAuth.post(`/board/${boardId}/member`, memberData);
}

export { createBoard, getBoard, addMember };
