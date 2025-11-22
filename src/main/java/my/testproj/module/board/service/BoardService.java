package my.testproj.module.board.service;

import lombok.RequiredArgsConstructor;
import my.testproj.module.board.mapper.BoardMapper;
import my.testproj.module.board.model.Board;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    /**
     * 게시판 맵퍼
     */
    private final BoardMapper boardMapper;

    /**
     * 게시글 등록
     */
    public int insertBoard(Board board) {
        return boardMapper.insertBoard(board);
    }

    /**
     * 게시글 단건 조회 (조회수 증가)
     */
    public Board selectBoardByBoardIdx(Long boardIdx) {
        boardMapper.updateBoardHits(boardIdx);
        return boardMapper.selectBoardByBoardIdx(boardIdx);
    }

    /**
     * 게시글 단건 조회 (조회수 증가 없음)
     */
    public Board selectBoardByBoardIdxWithoutHits(Long boardIdx) {
        return boardMapper.selectBoardByBoardIdx(boardIdx);
    }

    /**
     * 게시글 목록 조회
     */
    public List<Board> selectAllBoardList() {
        return boardMapper.selectAllBoardList();
    }

    /**
     * 게시글 수정
     */
    public int updateBoard(Board board) {
        return boardMapper.updateBoard(board);
    }

    /**
     * 게시글 삭제
     */
    public int deleteBoard(Long boardIdx) {
        return boardMapper.deleteBoard(boardIdx);
    }
}
