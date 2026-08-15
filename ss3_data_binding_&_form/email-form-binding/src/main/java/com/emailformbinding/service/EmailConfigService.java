package com.emailformbinding.service;

import com.emailformbinding.model.EmailConfig;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailConfigService implements IEmailConfigService {
    private EmailConfig currentConfig = new EmailConfig("English", 25, true, "King, Asgard");

    @Override
    public EmailConfig getConfig() {
        return currentConfig;
    }

    @Override
    public void updateConfig(EmailConfig config) {
        this.currentConfig = config;
    }

    @Override
    public List<String> getLanguages() {
        return Arrays.asList("English", "Vietnamese", "Japanese", "Chinese");
    }

    @Override
    public List<Integer> getPageSizes() {
        return Arrays.asList(5, 10, 15, 25, 50, 100);
    }
}
