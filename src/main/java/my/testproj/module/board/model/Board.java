package my.testproj.module.board.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Board {
    /**
     * 게시판 일련번호
     * */
    private Long id;
    /**
     * 제목
     * */
    private String title;
    /**
     * 내용
     * */
    private String contents;
    /**
     * 좋아요 수
     * */
    private Integer hits;
    /**
     * 작성일
     * */
    private LocalDateTime createAt;
}
