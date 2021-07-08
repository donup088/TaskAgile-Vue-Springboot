package com.taskagile.service.card;

import com.taskagile.controller.board.dto.BoardRequest;
import com.taskagile.controller.card.dto.CardRequest;
import com.taskagile.domain.board.Board;
import com.taskagile.domain.board.BoardRepository;
import com.taskagile.domain.card.Card;
import com.taskagile.domain.card.CardList;
import com.taskagile.domain.card.CardListRepository;
import com.taskagile.domain.card.CardRepository;
import com.taskagile.service.board.exception.BoardNotFoundException;
import com.taskagile.service.card.exception.CardListNotFoundException;
import com.taskagile.service.card.exception.CardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardListRepository cardListRepository;
    private final BoardRepository boardRepository;


    public CardList createCardList(CardRequest.CreateCardList request) {
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(BoardNotFoundException::new);

        return cardListRepository.save(CardList.create(request, board));
    }

    public Card createCard(CardRequest.CreateCard request) {
        CardList cardList = cardListRepository.findById(request.getCardListId()).orElseThrow(CardListNotFoundException::new);

        return cardRepository.save(Card.create(request, cardList));
    }

    public void swapPosition(CardRequest.SwapCardPosition request) {
        Card firstCard = cardRepository.findById(request.getFirstCardId()).orElseThrow(CardNotFoundException::new);
        Card secondCard = cardRepository.findById(request.getSecondCardId()).orElseThrow(CardNotFoundException::new);
        Card.swapPosition(firstCard, secondCard);
    }
}
