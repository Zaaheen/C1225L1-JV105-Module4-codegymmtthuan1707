package com.endmodule4.controller;

import com.endmodule4.dto.QuestionContentDto;
import com.endmodule4.entity.QuestionContent;
import com.endmodule4.entity.QuestionStatus;
import com.endmodule4.entity.QuestionType;
import com.endmodule4.service.QuestionContentService;
import com.endmodule4.service.QuestionTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private static final int PAGE_SIZE = 5;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final QuestionContentService questionService;
    private final QuestionTypeService typeService;

    @GetMapping({"", "/"})
    public String list(@RequestParam(required = false) String title,
                       @RequestParam(required = false) Long typeId,
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {
        int safePage = Math.max(page, 0);
        Page<QuestionContent> questions = questionService.search(title, typeId,
                PageRequest.of(safePage, PAGE_SIZE));
        model.addAttribute("questions", questions);
        model.addAttribute("questionTypes", typeService.findAll());
        model.addAttribute("titleFilter", title == null ? "" : title);
        model.addAttribute("typeFilter", typeId);
        model.addAttribute("pendingStatus", QuestionStatus.PENDING);
        return "question/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        prepareForm(model, new QuestionContentDto());
        return "question/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        QuestionContent question = questionService.findById(id);
        QuestionContentDto dto = new QuestionContentDto();
        dto.setId(question.getId());
        dto.setTitle(question.getTitle());
        dto.setContent(question.getContent());
        dto.setQuestionTypeId(question.getQuestionType().getId());
        prepareForm(model, dto);
        return "question/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("question") QuestionContentDto dto,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (dto.getQuestionTypeId() != null) {
            try {
                typeService.findById(dto.getQuestionTypeId());
            } catch (RuntimeException ex) {
                bindingResult.rejectValue("questionTypeId", "questionType.invalid", "Loại câu hỏi không tồn tại");
            }
        }
        if (bindingResult.hasErrors()) {
            prepareForm(model, dto);
            return "question/form";
        }
        if (dto.getId() == null) {
            questionService.save(dto);
            redirectAttributes.addFlashAttribute("success", "Thêm câu hỏi thành công");
        } else {
            questionService.update(dto.getId(), dto);
            redirectAttributes.addFlashAttribute("success", "Cập nhật câu hỏi thành công");
        }
        return "redirect:/questions";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        questionService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Xóa câu hỏi thành công");
        return "redirect:/questions";
    }

    @GetMapping("/{id}/detail")
    @ResponseBody
    public Map<String, Object> detail(@PathVariable Long id) {
        QuestionContent question = questionService.findById(id);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", question.getId());
        result.put("title", question.getTitle());
        result.put("content", question.getContent());
        result.put("questionTypeName", question.getQuestionType().getName());
        result.put("dateCreate", DATE_FORMAT.format(question.getDateCreate()));
        result.put("status", question.getStatus().name());
        result.put("answer", question.getAnswer() == null || question.getAnswer().isBlank() ? "N/A" : question.getAnswer());
        result.put("answeredBy", question.getAnsweredBy() == null || question.getAnsweredBy().isBlank() ? "N/A" : question.getAnsweredBy());
        return result;
    }

    private void prepareForm(Model model, QuestionContentDto dto) {
        List<QuestionType> types = typeService.findAll();
        model.addAttribute("question", dto);
        model.addAttribute("questionTypes", types);
        model.addAttribute("editing", dto.getId() != null);
    }
}
