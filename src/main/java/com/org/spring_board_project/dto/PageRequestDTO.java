package com.org.spring_board_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value=1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value=10)
    @Max(value=100)
    @Positive
    private int size = 10;

    private String[] types;    // 검색 종류 (t, w 등)
    private String keyword;    // 검색어
    private boolean finished;  // 완료 여부 (⭐ 현재 에러의 주범!)
    private LocalDate from;    // 시작 날짜
    private LocalDate to;      // 끝 날짜

    // 화면(JSP)에서 ${pageRequestDTO.checkType("t")} 형태로 호출하는 메서드
    public boolean checkType(String type) {
        if(types == null || types.length == 0) {
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }

    public int getSkip(){
        return (page-1)*10;
    }

    private String link;

    public String getLink() {
        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if(finished){
                builder.append("&finished=on");
            }

            if(types != null && types.length > 0){
                for (int i = 0; i < types.length; i++) {
                    builder.append("&types=" + types[i]);
                }
            }

            if(keyword != null){
                try {
                    builder.append("&keyword=" + java.net.URLEncoder.encode(keyword,"UTF-8"));
                } catch (java.io.UnsupportedEncodingException e) {}
            }

            if(from != null){
                builder.append("&from=" + from.toString());
            }

            if(to != null){
                builder.append("&to=" + to.toString());
            }
            link = builder.toString();
        }
        return link;
    }
}
