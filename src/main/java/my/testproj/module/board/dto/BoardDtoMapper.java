package my.testproj.module.board.dto;

import my.testproj.common.mapstruct.MapStructConfig;
import my.testproj.module.board.model.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 게시글 DTO 매퍼
 */
@Mapper(config = MapStructConfig.class, componentModel = "default", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardDtoMapper {
    /**
     * 매퍼 인스턴스
     */
    BoardDtoMapper INSTANCE = Mappers.getMapper(BoardDtoMapper.class);

    /**
     * 생성 요청을 엔티티로 변환
     */
    Board toEntity(BoardInsertDto boardInsertDto);

    /**
     * 수정 요청을 엔티티로 변환
     */
    @Mapping(target = "boardIdx", source = "boardIdx")
    Board toEntity(Long boardIdx, BoardUpdateRequestDto boardUpdateRequestDto);

    /**
     * 엔티티를 응답으로 변환
     */
    BoardResponseDto toResponse(Board board);
}
