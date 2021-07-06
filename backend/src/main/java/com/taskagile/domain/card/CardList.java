package com.taskagile.domain.card;

import com.taskagile.controller.card.dto.CardRequest;
import com.taskagile.domain.board.Board;
import com.taskagile.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "card_list")
public class CardList extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public static CardList create(CardRequest.CreateCardList request, Board board) {
        return CardList.builder().name(request.getName()).position(request.getPosition()).board(board).build();
    }
}
