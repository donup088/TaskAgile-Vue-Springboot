import { instanceWithAuth } from './index';

function createCardList(cardListData) {
	return instanceWithAuth.post('/card/list', cardListData);
}

function createCard(cardData) {
	return instanceWithAuth.post('/card', cardData);
}

export { createCardList, createCard };
