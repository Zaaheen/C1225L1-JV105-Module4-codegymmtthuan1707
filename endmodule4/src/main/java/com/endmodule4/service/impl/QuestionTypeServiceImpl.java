package com.endmodule4.service.impl;

import com.endmodule4.entity.QuestionType;
import com.endmodule4.exception.QuestionNotFoundException;
import com.endmodule4.repository.QuestionTypeRepository;
import com.endmodule4.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionTypeServiceImpl implements QuestionTypeService {
    private final QuestionTypeRepository repository;

    @Override
    public List<QuestionType> findAll() {
        return repository.findAll();
    }

    @Override
    public QuestionType findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Loại câu hỏi không tồn tại"));
    }
}
