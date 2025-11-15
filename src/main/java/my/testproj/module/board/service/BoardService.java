package my.testproj.module.board.service;

import lombok.RequiredArgsConstructor;
import my.testproj.module.board.mapper.BoardMapper;
import my.testproj.module.board.model.Board;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    /**
     * 게시글 등록
     * @param board 게시글 정보
     * @return 등록된 행 수
     */
    public int createBoard(Board board) {
        return boardMapper.insertBoard(board);
    }

    /**
     * 게시글 ID로 조회 (조회수 증가)
     * @param id 게시글 일련번호
     * @return 게시글 정보
     */
    public Board selectBoardById(Long id) {
        boardMapper.increaseBoardHits(id);
        return boardMapper.selectBoardById(id);
    }

    /**
     * 게시글 ID로 조회 (조회수 증가 없음)
     * @param id 게시글 일련번호
     * @return 게시글 정보
     */
    public Board selectBoardByIdWithoutHits(Long id) {
        return boardMapper.selectBoardById(id);
    }

    /**
     * 전체 게시글 목록 조회
     * @return 게시글 목록
     */
    public List<Board> selectAllBoards() {
        return boardMapper.selectAllBoards();
    }

    /**
     * 게시글 수정
     * @param board 수정할 게시글 정보
     * @return 수정된 행 수
     */
    public int updateBoard(Board board) {
        return boardMapper.updateBoard(board);
    }

    /**
     * 게시글 삭제
     * @param id 게시글 일련번호
     * @return 삭제된 행 수
     */
    public int deleteBoard(Long id) {
        return boardMapper.deleteBoard(id);
    }
}
