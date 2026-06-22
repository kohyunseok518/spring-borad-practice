package com.org.spring_board_project.service;

import com.org.spring_board_project.domain.TodoVO;
import com.org.spring_board_project.dto.PageRequestDTO;
import com.org.spring_board_project.dto.PageResponseDTO;
import com.org.spring_board_project.dto.TodoDTO;

import java.util.*;

public interface TodoService {

    void register(TodoDTO todoDTO);

//    List<TodoDTO> getAll();

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);
}
