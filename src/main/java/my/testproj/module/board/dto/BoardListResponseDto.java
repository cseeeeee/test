package my.testproj.module.board.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 목록 응답 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListResponseDto {
    /**
     * 게시글 일련번호
     */
    private Long boardIdx;
    /**
     * 제목
     */
    private String title;
    /**
     * 조회수
     */
    private Integer hits;
    /**
     * 작성일
     */
    private LocalDateTime createAt;
}
