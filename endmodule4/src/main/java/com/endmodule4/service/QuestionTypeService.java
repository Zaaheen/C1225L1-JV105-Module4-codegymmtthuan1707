package com.endmodule4.service;

import com.endmodule4.entity.QuestionType;

import java.util.List;

public interface QuestionTypeService {
    List<QuestionType> findAll();

    QuestionType findById(Long id);
}
