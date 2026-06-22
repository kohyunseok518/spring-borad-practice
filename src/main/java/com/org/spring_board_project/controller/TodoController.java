package com.org.spring_board_project.controller;

import com.org.spring_board_project.dto.PageRequestDTO;
import com.org.spring_board_project.dto.TodoDTO;
import com.org.spring_board_project.service.TodoService;
import com.org.spring_board_project.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor // final이 붙은 변수들을 자동으로 주입
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()) {
            pageRequestDTO = pageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register....");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("POST todo register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        // 🚀 확인용 로그 추가!
        log.info("--- 들어온 데이터 확인 ---");
        log.info("Title: " + todoDTO.getTitle());
        if(todoDTO.getFiles() != null) {
            log.info("파일 개수: " + todoDTO.getFiles().size());
        } else {
            log.info("파일 리스트가 null입니다!");
        }

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long tno, Model model) {
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes) {
        log .info(" -------------remove------------------");
        log .info("tno: " + tno);
        todoService.remove(tno);
        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping("/download/{ano}")
    @ResponseBody
    public void download(@PathVariable("ano") Long ano, javax.servlet.http.HttpServletResponse response) throws Exception {
        com.org.spring_board_project.domain.TodoAttachmentVO attach = todoService.getAttachment(ano);
        if (attach == null) {
            response.sendError(javax.servlet.http.HttpServletResponse.SC_NOT_FOUND); return;
        }
        java.io.File file = new java.io.File(attach.getPath());
        com.org.spring_board_project.util.UploadFiles.download(response, file, attach.getFilename());
    }
}
