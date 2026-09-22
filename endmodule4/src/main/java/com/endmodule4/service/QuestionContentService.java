package com.endmodule4.service;

import com.endmodule4.dto.QuestionContentDto;
import com.endmodule4.entity.QuestionContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionContentService {
    Page<QuestionContent> search(String title, Long typeId, Pageable pageable);

    QuestionContent findById(Long id);

    QuestionContent save(QuestionContentDto dto);

    QuestionContent update(Long id, QuestionContentDto dto);

    void delete(Long id);
}
