package my.testproj.module.board.mapstruct;

import java.util.List;
import my.testproj.common.mapstruct.MapStructConfig;
import my.testproj.module.board.dto.BoardDeleteResponseDto;
import my.testproj.module.board.dto.BoardDetailResponseDto;
import my.testproj.module.board.dto.BoardInsertDto;
import my.testproj.module.board.dto.BoardInsertResponseDto;
import my.testproj.module.board.dto.BoardListResponseDto;
import my.testproj.module.board.dto.BoardUpdateRequestDto;
import my.testproj.module.board.dto.BoardUpdateResponseDto;
import my.testproj.module.board.model.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 게시판 도메인 ↔ DTO 변환 매퍼
 */
@Mapper(config = MapStructConfig.class)
public interface BoardMapStruct {

    /**
     * BoardInsertDto > Board 변환
     */
    Board toModel(BoardInsertDto boardInsertDto);

    /**
     * BoardUpdateRequestDto > Board 변환
     */
    @Mapping(target = "boardIdx", source = "boardIdx")
    Board toModel(Long boardIdx, BoardUpdateRequestDto boardUpdateRequestDto);

    /**
     * Board > BoardDetailResponseDto 변환
     */
    BoardDetailResponseDto toDetailDto(Board board);

    /**
     * Board > BoardInsertResponseDto 변환
     */
    BoardInsertResponseDto toInsertDto(Board board);

    /**
     * Board > BoardUpdateResponseDto 변환
     */
    BoardUpdateResponseDto toUpdateDto(Board board);

    /**
     * List<Board> > List<BoardListResponseDto> 변환
     */
    List<BoardListResponseDto> toListDto(List<Board> boardList);

    /**
     * 삭제 응답 변환 (메시지를 포함하기 위해 수동 매핑)
     */
    default BoardDeleteResponseDto toDeleteDto(Long boardIdx, boolean deleted) {
        return BoardDeleteResponseDto.builder()
                .boardIdx(boardIdx)
                .deleted(deleted)
                .message(deleted ? "게시글이 삭제되었습니다." : "게시글 삭제에 실패했습니다.")
                .build();
    }
}
