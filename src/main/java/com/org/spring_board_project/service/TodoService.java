package com.org.spring_board_project.service;

import com.org.spring_board_project.domain.TodoAttachmentVO;
import com.org.spring_board_project.domain.TodoVO;
import com.org.spring_board_project.dto.PageRequestDTO;
import com.org.spring_board_project.dto.PageResponseDTO;
import com.org.spring_board_project.dto.TodoDTO;

import java.util.*;

public interface TodoService {

    void register(TodoDTO todoDTO);

//    List<TodoDTO> getAll();

    // 파일을 하나 가져오는 메서드
    TodoAttachmentVO getAttachment(Long ano);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);
}
