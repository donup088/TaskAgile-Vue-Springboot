package com.taskagile.domain.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taskagile.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.taskagile.domain.board.QBoard.*;
import static com.taskagile.domain.board.QBoardUser.*;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Board> findAllByUserInBoardUser(User user) {
        return queryFactory.selectFrom(board)
                .join(board.boardUsers, boardUser).on(board.id.eq(boardUser.board.id))
                .where(boardUser.user.id.eq(user.getId()))
                .fetch();
    }
}
