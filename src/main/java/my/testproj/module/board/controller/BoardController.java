package my.testproj.module.board.controller;

import lombok.RequiredArgsConstructor;
import my.testproj.module.board.model.Board;
import my.testproj.module.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /**
     * 게시글 등록
     * @param board 게시글 정보
     * @return 등록 결과 메시지
     */
    @PostMapping
    public ResponseEntity<String> createBoard(@RequestBody Board board) {
        int result = boardService.createBoard(board);
        return result > 0
            ? ResponseEntity.ok("게시글이 등록되었습니다.")
            : ResponseEntity.badRequest().body("게시글 등록에 실패했습니다.");
    }

    /**
     * 전체 게시글 목록 조회
     * @return 게시글 목록
     */
    @GetMapping
    public ResponseEntity<List<Board>> selectAllBoards() {
        List<Board> boards = boardService.selectAllBoards();
        return ResponseEntity.ok(boards);
    }

    /**
     * 게시글 상세 조회 (조회수 증가)
     * @param id 게시글 일련번호
     * @return 게시글 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<Board> selectBoardById(@PathVariable Long id) {
        Board board = boardService.selectBoardById(id);
        return board != null
            ? ResponseEntity.ok(board)
            : ResponseEntity.notFound().build();
    }

    /**
     * 게시글 수정
     * @param id 게시글 일련번호
     * @param board 수정할 게시글 정보
     * @return 수정 결과 메시지
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody Board board) {
        board.setId(id);
        int result = boardService.updateBoard(board);
        return result > 0
            ? ResponseEntity.ok("게시글이 수정되었습니다.")
            : ResponseEntity.badRequest().body("게시글 수정에 실패했습니다.");
    }

    /**
     * 게시글 삭제
     * @param id 게시글 일련번호
     * @return 삭제 결과 메시지
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        int result = boardService.deleteBoard(id);
        return result > 0
            ? ResponseEntity.ok("게시글이 삭제되었습니다.")
            : ResponseEntity.badRequest().body("게시글 삭제에 실패했습니다.");
    }
}
