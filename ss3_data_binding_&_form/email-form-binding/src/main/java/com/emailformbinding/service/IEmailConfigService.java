package com.emailformbinding.service;

import com.emailformbinding.model.EmailConfig;

import java.util.List;

public interface IEmailConfigService {
    EmailConfig getConfig();
    void updateConfig(EmailConfig config);
    List<String> getLanguages();
    List<Integer> getPageSizes();
}
