package my.testproj.module.board.model;

import org.mapstruct.factory.Mappers;

public interface BoardMapStruct {

    /**
     * mapStruct 매퍼 인터페이스
     */
    BoardMapStruct INSTANCE = Mappers.getMapper(BoardMapStruct.class);

    /**
     * 게시판 목록 조회
     * */
//    List<>
}
