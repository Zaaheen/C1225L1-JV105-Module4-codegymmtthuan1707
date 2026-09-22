package com.endmodule4.repository;

import com.endmodule4.entity.QuestionContent;
import com.endmodule4.entity.QuestionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionContentRepository extends JpaRepository<QuestionContent, Long> {
    @Query("""
            SELECT q FROM QuestionContent q
            WHERE (:title IS NULL OR LOWER(q.title) LIKE LOWER(CONCAT('%', :title, '%')))
              AND (:typeId IS NULL OR q.questionType.id = :typeId)
            ORDER BY CASE WHEN q.status = :pendingStatus THEN 0 ELSE 1 END,
                     q.dateCreate DESC
            """)
    Page<QuestionContent> search(@Param("title") String title,
                                 @Param("typeId") Long typeId,
                                 @Param("pendingStatus") QuestionStatus pendingStatus,
                                 Pageable pageable);
}
