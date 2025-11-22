package my.testproj.module.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 삭제 응답 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDeleteResponseDto {
    /**
     * 게시글 일련번호
     */
    private Long boardIdx;
    /**
     * 삭제 성공 여부
     */
    private boolean deleted;
    /**
     * 결과 메시지
     */
    private String message;
}
