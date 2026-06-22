package com.org.spring_board_project.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;

    // 첨부파일을 여러 개 담을 리스트
    private List<TodoAttachmentVO> attaches;
}
