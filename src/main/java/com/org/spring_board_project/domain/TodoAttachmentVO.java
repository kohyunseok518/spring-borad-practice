package com.org.spring_board_project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoAttachmentVO {
    private Long ano; // 첨부파일 고유 번호
    private Long tno; // 속해있는 Todo 게시글 번호
    private String filename;
    private String path;
    private String contentType;
    private Long size;
    private Date regDate;

    // 👇 외부에서 깔끔하게 객체를 조립할 수 있게 도와주는 메서드 추가
    public static TodoAttachmentVO of(MultipartFile part, Long tno, String path) {
        return TodoAttachmentVO.builder()
                .tno(tno)
                .filename(part.getOriginalFilename())
                .path(path)
                .contentType(part.getContentType())
                .size(part.getSize())
                .build();
    }
}
