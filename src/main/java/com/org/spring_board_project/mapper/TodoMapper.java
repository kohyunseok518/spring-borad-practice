package com.org.spring_board_project.mapper;

import com.org.spring_board_project.domain.TodoAttachmentVO;
import com.org.spring_board_project.domain.TodoVO;
import com.org.spring_board_project.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);

    // 목록 및 count 처리
    List<TodoVO>selectList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);

    // 첨부파일을 DB에 저장하는 기능
    void insertAttachment(TodoAttachmentVO attach);
    TodoAttachmentVO getAttachment(Long ano);
}
