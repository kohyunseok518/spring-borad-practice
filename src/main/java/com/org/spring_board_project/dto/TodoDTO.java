package com.org.spring_board_project.dto;

import com.org.spring_board_project.domain.TodoAttachmentVO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    @NotEmpty
    private String title;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private boolean finished;

    @NotEmpty
    private String writer;

    // 등록할 때 실제 쓸 파일들
    private List<MultipartFile> files;

    // DB에서 꺼내서 화면에 보여줄 첨부파일 정보들
    private List<TodoAttachmentVO> attaches;
}
