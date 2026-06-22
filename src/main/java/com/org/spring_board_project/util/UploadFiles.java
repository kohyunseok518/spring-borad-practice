package com.org.spring_board_project.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public class UploadFiles {
    // 파일 저장 메서드
    public static String upload(String baseDir, MultipartFile part) throws IOException {
        File base = new File(baseDir);
        if(!base.exists()) {
            base.mkdir();
        }

        String uniqueName = UploadFileName.getUniqueName(part.getOriginalFilename());
        File dest = new File(baseDir, uniqueName);

        part.transferTo(dest); // 실제 물리적 저장!
        return dest.getPath(); // 저장된 전체 경로 반환
    }

    // 파일 다운로드 메서드
    public static void download(HttpServletResponse response, File file, String originalFilename) throws IOException {
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String encodedFilename = URLEncoder.encode(originalFilename, "UTF-8").replaceAll("\\+", "%20");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\"");

        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int b;
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        }
    }
}
