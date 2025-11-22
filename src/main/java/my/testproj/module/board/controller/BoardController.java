package my.testproj.module.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.testproj.module.board.dto.BoardDtoMapper;
import my.testproj.module.board.dto.BoardInsertDto;
import my.testproj.module.board.dto.BoardResponseDto;
import my.testproj.module.board.dto.BoardUpdateRequestDto;
import my.testproj.module.board.model.Board;
import my.testproj.module.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    /**
     * 게시판 서비스
     */
    private final BoardService boardService;
    /**
     * 게시판 DTO 매퍼
     */
    private final BoardDtoMapper boardDtoMapper = BoardDtoMapper.INSTANCE;

    /**
     * 게시글 등록
     */
    @PostMapping
    public ResponseEntity<BoardResponseDto> insertBoard(@Valid @RequestBody BoardInsertDto boardInsertDto) {
        Board board = boardDtoMapper.toEntity(boardInsertDto);
        int insertedCount = boardService.insertBoard(board);
        return insertedCount > 0
                ? ResponseEntity.ok(boardDtoMapper.toResponse(boardService.selectBoardByBoardIdxWithoutHits(board.getBoardIdx())))
                : ResponseEntity.badRequest().build();
    }

    /**
     * 게시글 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> selectBoardList() {
        List<BoardResponseDto> boardResponseDtoList = boardService.selectAllBoardList().stream()
                .map(boardDtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(boardResponseDtoList);
    }

    /**
     * 게시글 상세 조회 (조회수 증가)
     */
    @GetMapping("/{boardIdx}")
    public ResponseEntity<BoardResponseDto> selectBoardByBoardIdx(@PathVariable Long boardIdx) {
        Board board = boardService.selectBoardByBoardIdx(boardIdx);
        return board != null
                ? ResponseEntity.ok(boardDtoMapper.toResponse(board))
                : ResponseEntity.notFound().build();
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{boardIdx}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long boardIdx, @Valid @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        int updatedCount = boardService.updateBoard(boardDtoMapper.toEntity(boardIdx, boardUpdateRequestDto));
        if (updatedCount <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Board updatedBoard = boardService.selectBoardByBoardIdxWithoutHits(boardIdx);
        return ResponseEntity.ok(boardDtoMapper.toResponse(updatedBoard));
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{boardIdx}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardIdx) {
        int result = boardService.deleteBoard(boardIdx);
        return result > 0
            ? ResponseEntity.ok("게시글이 삭제되었습니다.")
            : ResponseEntity.badRequest().body("게시글 삭제에 실패했습니다.");
    }
}
