package com.endmodule4.service.impl;

import com.endmodule4.dto.QuestionContentDto;
import com.endmodule4.entity.QuestionContent;
import com.endmodule4.entity.QuestionStatus;
import com.endmodule4.exception.QuestionNotFoundException;
import com.endmodule4.repository.QuestionContentRepository;
import com.endmodule4.repository.QuestionTypeRepository;
import com.endmodule4.service.QuestionContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionContentServiceImpl implements QuestionContentService {
    private final QuestionContentRepository questionRepository;
    private final QuestionTypeRepository typeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionContent> search(String title, Long typeId, Pageable pageable) {
        String normalizedTitle = title == null || title.isBlank() ? null : title.trim();
        return questionRepository.search(normalizedTitle, typeId, QuestionStatus.PENDING, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionContent findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Câu hỏi không tồn tại"));
    }

    @Override
    @Transactional
    public QuestionContent save(QuestionContentDto dto) {
        QuestionContent question = new QuestionContent();
        question.setDateCreate(LocalDateTime.now());
        question.setStatus(QuestionStatus.PENDING);
        question.setAnswer(null);
        applyQuestionData(question, dto);
        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public QuestionContent update(Long id, QuestionContentDto dto) {
        QuestionContent question = findById(id);
        applyQuestionData(question, dto);
        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        QuestionContent question = findById(id);
        questionRepository.delete(question);
    }

    private void applyQuestionData(QuestionContent question, QuestionContentDto dto) {
        question.setTitle(dto.getTitle().trim());
        question.setContent(dto.getContent().trim());
        question.setQuestionType(typeRepository.findById(dto.getQuestionTypeId())
                .orElseThrow(() -> new QuestionNotFoundException("Loại câu hỏi không tồn tại")));
    }
}
