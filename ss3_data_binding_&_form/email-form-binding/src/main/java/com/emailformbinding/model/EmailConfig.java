package com.emailformbinding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfig {
    private String language;
    private int pageSize;
    private boolean spamsFilter;
    private String signature;
}
