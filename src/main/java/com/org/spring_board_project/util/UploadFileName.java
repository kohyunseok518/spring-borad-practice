package com.org.spring_board_project.util;

import java.util.UUID;

public class UploadFileName {
    public static String getUniqueName(String filename) {
        String uuid = UUID.randomUUID().toString();
        // 파일명에 포함될 수 있는 특수문자나 공백을 언더바로 치환하여 안전하게 만들기
        String cleanName = filename.replaceAll("[\\\\/:*?\"<>|\\s]", "_");
        return uuid + "_" + cleanName;
    }
}
