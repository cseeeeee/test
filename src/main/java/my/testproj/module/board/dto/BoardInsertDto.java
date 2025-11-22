package my.testproj.module.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 게시글 등록 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsertDto {

    /**
     * 게시글 제목
     */
    @NotBlank
    private String title;

    /**
     * 게시글 내용
     */
    @NotBlank
    private String contents;

    /**
     * 생성 일시
     */
    private LocalDateTime createAt;
}
