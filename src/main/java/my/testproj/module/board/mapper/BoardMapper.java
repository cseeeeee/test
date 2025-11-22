package my.testproj.module.board.mapper;

import my.testproj.module.board.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BoardMapper {
    /**
     * 게시글 등록
     */
    int insertBoard(Board board);

    /**
     * 게시글 일련번호로 조회
     */
    Board selectBoardByBoardIdx(@Param("boardIdx") Long boardIdx);

    /**
     * 전체 게시글 목록 조회
     */
    List<Board> selectAllBoardList();

    /**
     * 게시글 수정
     */
    int updateBoard(Board board);

    /**
     * 게시글 조회수 증가
     */
    int updateBoardHits(@Param("boardIdx") Long boardIdx);

    /**
     * 게시글 삭제
     */
    int deleteBoard(@Param("boardIdx") Long boardIdx);
}
