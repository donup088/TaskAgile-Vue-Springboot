package com.taskagile.domain.board;

import com.taskagile.controller.board.dto.BoardRequest;
import com.taskagile.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    public static Board createPersonal(BoardRequest.create request) {
        return Board.builder()
                .name(request.getName())
                .description(request.getDescription())
                .boardType(BoardType.PERSONAL)
                .build();
    }
}
