package my.testproj.module.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 수정 요청 DTO
 */
@Getter
@Setter
public class BoardUpdateRequestDto {
    /**
     * 제목
     */
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    /**
     * 내용
     */
    @NotBlank(message = "내용은 필수입니다.")
    private String contents;
}
