import { instanceWithAuth } from './index';

function createCardList(cardListData) {
	return instanceWithAuth.post('/card/list', cardListData);
}

function createCard(cardData) {
	return instanceWithAuth.post('/card', cardData);
}

function changeCardPosition(cardData) {
	return instanceWithAuth.post('/card/position', cardData);
}

export { createCardList, createCard, changeCardPosition };
