package my.testproj.module.board.service;

import lombok.RequiredArgsConstructor;
import my.testproj.module.board.dto.BoardInsertResponseDto;
import my.testproj.module.board.dto.BoardDeleteResponseDto;
import my.testproj.module.board.dto.BoardDetailResponseDto;
import my.testproj.module.board.dto.BoardListResponseDto;
import my.testproj.module.board.dto.BoardUpdateResponseDto;
import my.testproj.module.board.mapper.BoardMapper;
import my.testproj.module.board.dto.BoardInsertDto;
import my.testproj.module.board.dto.BoardUpdateRequestDto;
import my.testproj.module.board.model.Board;
import my.testproj.module.board.mapstruct.BoardMapStruct;
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
     * Board DTO <-> Model 매퍼
     */
    private final BoardMapStruct boardMapStruct;

    /**
     * 게시글 등록 (DTO 반환)
     */
    public BoardInsertResponseDto insertBoard(BoardInsertDto boardInsertDto) {
        Board board = boardMapStruct.toModel(boardInsertDto);
        int inserted = boardMapper.insertBoard(board);
        if (inserted <= 0) {
            return null;
        }
        Board persisted = selectBoardByBoardIdxWithoutHits(board.getBoardIdx());
        return persisted != null ? boardMapStruct.toInsertDto(persisted) : null;
    }

    /**
     * 게시글 단건 조회 (조회수 증가)
     */
    public Board selectBoardByBoardIdx(Long boardIdx) {
        boardMapper.updateBoardHits(boardIdx);
        return boardMapper.selectBoardByBoardIdx(boardIdx);
    }

    /**
     * 게시글 단건 조회 DTO (조회수 증가)
     */
    public BoardDetailResponseDto selectBoardDetail(Long boardIdx) {
        Board board = selectBoardByBoardIdx(boardIdx);
        return board != null ? boardMapStruct.toDetailDto(board) : null;
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
     * 게시글 목록 조회 DTO
     */
    public List<BoardListResponseDto> selectBoardListDto() {
        return boardMapStruct.toListDto(boardMapper.selectAllBoardList());
    }

    /**
     * 게시글 수정 (DTO 반환)
     */
    public BoardUpdateResponseDto updateBoard(Long boardIdx, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardMapStruct.toModel(boardIdx, boardUpdateRequestDto);
        int updated = boardMapper.updateBoard(board);
        if (updated <= 0) {
            return null;
        }
        Board persisted = selectBoardByBoardIdxWithoutHits(boardIdx);
        return persisted != null ? boardMapStruct.toUpdateDto(persisted) : null;
    }

    /**
     * 게시글 삭제 (DTO 반환)
     */
    public BoardDeleteResponseDto deleteBoard(Long boardIdx) {
        int deleted = boardMapper.deleteBoard(boardIdx);
        return boardMapStruct.toDeleteDto(boardIdx, deleted > 0);
    }
}
