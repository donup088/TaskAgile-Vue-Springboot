package com.taskagile.controller.card;

import com.taskagile.controller.card.dto.CardRequest;
import com.taskagile.controller.card.dto.CardResponse;
import com.taskagile.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/card")
public class CardController {
    private final CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse.CreateCard createCard(@RequestBody @Valid CardRequest.CreateCard request) {
        return CardResponse.CreateCard.build(cardService.createCard(request));
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse.CreateCardList createCardList(@RequestBody @Valid CardRequest.CreateCardList request) {
        return CardResponse.CreateCardList.build(cardService.createCardList(request));
    }

}
