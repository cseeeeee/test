package my.testproj.module.board.mapper;

import my.testproj.module.board.model.Board;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardMapper {
    /**
     * 게시글 등록
     * @param board 게시글 정보
     * @return 등록된 행 수
     */
    int insertBoard(Board board);

    /**
     * 게시글 ID로 조회
     * @param id 게시글 일련번호
     * @return 게시글 정보
     */
    Board selectBoardById(Long id);

    /**
     * 전체 게시글 목록 조회
     * @return 게시글 목록
     */
    List<Board> selectAllBoards();

    /**
     * 게시글 수정
     * @param board 수정할 게시글 정보
     * @return 수정된 행 수
     */
    int updateBoard(Board board);

    /**
     * 게시글 조회수 증가
     * @param id 게시글 일련번호
     * @return 수정된 행 수
     */
    int increaseBoardHits(Long id);

    /**
     * 게시글 삭제
     * @param id 게시글 일련번호
     * @return 삭제된 행 수
     */
    int deleteBoard(Long id);
}