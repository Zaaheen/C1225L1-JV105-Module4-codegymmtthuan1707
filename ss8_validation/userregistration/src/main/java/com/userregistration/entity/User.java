package com.userregistration.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name không được để trống")
    @Size(min = 5, max = 45, message = "First name phải có độ dài từ 5 đến 45 ký tự")
    @Pattern(regexp = "^[A-Z\\p{Lu}].*", message = "Ký tự đầu tiên của First name phải viết hoa")
    @Column(nullable = false, length = 45)
    private String firstName;

    @NotBlank(message = "Last name không được để trống")
    @Size(min = 5, max = 45, message = "Last name phải có độ dài từ 5 đến 45 ký tự")
    @Pattern(regexp = "^[A-Z\\p{Lu}].*", message = "Ký tự đầu tiên của Last name phải viết hoa")
    @Column(nullable = false, length = 45)
    private String lastName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại không đúng định dạng (Ví dụ: 0912345678)")
    @Column(length = 15)
    private String phoneNumber;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 18, message = "Tuổi phải lớn hơn hoặc bằng 18")
    private Integer age;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng (Ví dụ: example@domain.com)")
    private String email;
}
