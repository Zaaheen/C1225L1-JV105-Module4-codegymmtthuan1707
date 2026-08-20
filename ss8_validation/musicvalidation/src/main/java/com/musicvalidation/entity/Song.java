package com.musicvalidation.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không được vượt quá 800 ký tự")
    @Pattern(regexp = "^[^@;,.=\\-+]+$", message = "Tên bài hát không được chứa các ký tự đặc biệt (@ ; , . = - + ...)")
    @Column(nullable = false, length = 800)
    private String name;

    @NotBlank(message = "Tên nghệ sĩ không được để trống")
    @Size(max = 300, message = "Tên nghệ sĩ không được vượt quá 300 ký tự")
    @Pattern(regexp = "^[^@;,.=\\-+]+$", message = "Tên nghệ sĩ không được chứa các ký tự đặc biệt (@ ; , . = - + ...)")
    @Column(nullable = false, length = 300)
    private String artist;

    @NotBlank(message = "Thể loại nhạc không được để trống")
    @Size(max = 1000, message = "Thể loại không được vượt quá 1000 ký tự")
    @Pattern(regexp = "^[^@;.=\\-+]+$", message = "Thể loại không được chứa ký tự đặc biệt (ngoại trừ dấu phẩy ',')")
    @Column(nullable = false, length = 1000)
    private String genre;
}
