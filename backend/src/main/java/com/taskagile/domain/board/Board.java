package com.taskagile.domain.board;

import com.taskagile.controller.board.dto.BoardRequest;
import com.taskagile.domain.common.BaseEntity;
import com.taskagile.domain.team.Team;
import com.taskagile.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private final List<BoardUser> boardUsers = new ArrayList<>();

    public static Board create(BoardRequest.create request, Team team, User user) {
        Board board = Board.builder()
                .name(request.getName())
                .description(request.getDescription())
                .team(team)
                .build();
        BoardUser.create(board, user);
        return board;
    }
}
