package com.endmodule4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionContentDto {
    private Long id;

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 100, message = "Tiêu đề phải từ 5 đến 100 ký tự")
    private String title;

    @NotBlank(message = "Nội dung không được để trống")
    @Size(min = 10, max = 500, message = "Nội dung phải từ 10 đến 500 ký tự")
    private String content;

    @NotNull(message = "Vui lòng chọn loại câu hỏi")
    private Long questionTypeId;
}
