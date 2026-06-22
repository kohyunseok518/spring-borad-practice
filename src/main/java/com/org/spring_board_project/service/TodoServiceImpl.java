package com.org.spring_board_project.service;

import com.org.spring_board_project.domain.TodoAttachmentVO;
import com.org.spring_board_project.domain.TodoVO;
import com.org.spring_board_project.dto.PageRequestDTO;
import com.org.spring_board_project.dto.PageResponseDTO;
import com.org.spring_board_project.dto.TodoDTO;
import com.org.spring_board_project.mapper.TodoMapper;
import com.org.spring_board_project.util.UploadFiles;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    // 자동 데이터 복사기
    private final TodoMapper todoMapper;
    // DB에서 데이터를 넣고 빼올 창고 관리자
    private final ModelMapper modelMapper;

    // 저장 폴더 경로 설장
    private final String UPLOAD_DIR = "/Users/kohyunseok/Desktop/upload";

    @Override
    @Transactional
    public void register(TodoDTO todoDTO) {
        log.info(modelMapper);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.insert(todoVO);
        Long tno = todoVO.getTno();

        log.info(todoVO);

        // 첨부파일이 존재하면 파일 저장 로직 실행
        if (todoDTO.getFiles() != null && !todoDTO.getFiles().isEmpty()) {
            for (MultipartFile file : todoDTO.getFiles()) {
                if (file.isEmpty()) continue;

                try {
                    // 1. 유틸리티를 써서 물리적 파일 저장
                    String uploadPath = UploadFiles.upload(UPLOAD_DIR, file);

                    // 2. VO 객체를 깔끔하게 생성
                    TodoAttachmentVO attach = TodoAttachmentVO.of(file, tno, uploadPath);

                    // 3. DB에 INSERT
                    todoMapper.insertAttachment(attach);

                } catch (IOException e) {
                    // 에러 발생 시 트랜잭션 롤백 유도!
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 파일을 하나 가져오는 메서드
    @Override
    public TodoAttachmentVO getAttachment(Long ano) {
        return todoMapper.getAttachment(ano);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        int total = todoMapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();


        return pageResponseDTO;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoMapper.update(todoVO);
    }
}
