package my.testproj.module.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.testproj.module.board.dto.BoardInsertResponseDto;
import my.testproj.module.board.dto.BoardDeleteResponseDto;
import my.testproj.module.board.dto.BoardDetailResponseDto;
import my.testproj.module.board.dto.BoardInsertDto;
import my.testproj.module.board.dto.BoardListResponseDto;
import my.testproj.module.board.dto.BoardUpdateRequestDto;
import my.testproj.module.board.dto.BoardUpdateResponseDto;
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

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    /**
     * 게시판 서비스
     */
    private final BoardService boardService;

    /**
     * 게시글 등록
     */
    @PostMapping
    public ResponseEntity<BoardInsertResponseDto> insertBoard(@Valid @RequestBody BoardInsertDto boardInsertDto) {
        BoardInsertResponseDto responseDto = boardService.insertBoard(boardInsertDto);
        return responseDto != null
                ? ResponseEntity.ok(responseDto)
                : ResponseEntity.badRequest().build();
    }

    /**
     * 게시글 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<BoardListResponseDto>> selectBoardList() {
        List<BoardListResponseDto> responseList = boardService.selectBoardListDto();
        return ResponseEntity.ok(responseList);
    }

    /**
     * 게시글 상세 조회 (조회수 증가)
     */
    @GetMapping("/{boardIdx}")
    public ResponseEntity<BoardDetailResponseDto> selectBoardByBoardIdx(@PathVariable Long boardIdx) {
        BoardDetailResponseDto responseDto = boardService.selectBoardDetail(boardIdx);
        return responseDto != null
                ? ResponseEntity.ok(responseDto)
                : ResponseEntity.notFound().build();
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{boardIdx}")
    public ResponseEntity<BoardUpdateResponseDto> updateBoard(@PathVariable Long boardIdx, @Valid @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        BoardUpdateResponseDto responseDto = boardService.updateBoard(boardIdx, boardUpdateRequestDto);
        return responseDto != null
                ? ResponseEntity.ok(responseDto)
                : ResponseEntity.badRequest().build();
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{boardIdx}")
    public ResponseEntity<BoardDeleteResponseDto> deleteBoard(@PathVariable Long boardIdx) {
        BoardDeleteResponseDto response = boardService.deleteBoard(boardIdx);
        return response.isDeleted()
            ? ResponseEntity.ok(response)
            : ResponseEntity.badRequest().body(response);
    }
}
