package com.musicvalidation.controller;


import com.musicvalidation.entity.Song;
import com.musicvalidation.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;

    // 1. Danh sách bài hát
    @GetMapping
    public String listSongs(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "song/list";
    }

    // 2. Hiển thị form thêm mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        return "song/form";
    }

    // 3. Hiển thị form cập nhật
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Song> songOptional = songService.findById(id);
        if (songOptional.isPresent()) {
            model.addAttribute("song", songOptional.get());
            return "song/form";
        }
        redirect.addFlashAttribute("error", "Không tìm thấy bài hát!");
        return "redirect:/songs";
    }

    // 4. Xử lý lưu bài hát (Chung cho cả Thêm mới & Cập nhật)
    @PostMapping("/save")
    public String saveSong(@Valid @ModelAttribute("song") Song song,
                           BindingResult bindingResult,
                           RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "song/form";
        }
        boolean isNew = (song.getId() == null);
        songService.save(song);

        String msg = isNew ? "Thêm bài hát mới thành công!" : "Cập nhật bài hát thành công!";
        redirect.addFlashAttribute("message", msg);
        return "redirect:/songs";
    }
}
