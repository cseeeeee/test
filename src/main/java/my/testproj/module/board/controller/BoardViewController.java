package my.testproj.module.board.controller;

import lombok.RequiredArgsConstructor;
import my.testproj.module.board.model.Board;
import my.testproj.module.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 게시판 뷰 컨트롤러
 */
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardViewController {
    private final BoardService boardService;

    /**
     * 게시글 목록 페이지
     */
    @GetMapping
    public String listPage(Model model) {
        List<Board> boardList = boardService.selectAllBoardList();
        model.addAttribute("boards", boardList);
        return "board/list";
    }

    /**
     * 게시글 상세 페이지
     */
    @GetMapping("/{boardIdx}")
    public String detailPage(@PathVariable Long boardIdx, Model model) {
        Board board = boardService.selectBoardByBoardIdx(boardIdx);
        model.addAttribute("board", board);
        return "board/detail";
    }

    /**
     * 게시글 작성 페이지
     */
    @GetMapping("/new")
    public String createPage(Model model) {
        model.addAttribute("board", new Board());
        return "board/form";
    }

    /**
     * 게시글 수정 페이지
     */
    @GetMapping("/{boardIdx}/edit")
    public String editPage(@PathVariable Long boardIdx, Model model) {
        Board board = boardService.selectBoardByBoardIdxWithoutHits(boardIdx);
        model.addAttribute("board", board);
        return "board/form";
    }
}
