package com.org.spring_board_project.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor// 알아서 final 필드들의 생성자를 만들고 빈을 주입함
public class SampleService {

//    @Autowired
    // final이 붙은 해당 참조 변수가 자동 주입 대상이 됨
    private final SampleDAO sampleDAO;
}
