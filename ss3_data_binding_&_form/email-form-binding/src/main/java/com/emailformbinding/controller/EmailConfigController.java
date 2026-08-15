package com.emailformbinding.controller;


import com.emailformbinding.model.EmailConfig;
import com.emailformbinding.service.IEmailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmailConfigController {
    private final IEmailConfigService emailConfigService;

    @Autowired
    public EmailConfigController(IEmailConfigService emailConfigService) {
        this.emailConfigService = emailConfigService;
    }
    @GetMapping({"/", "/config"})
    public String showConfig(Model model) {
        model.addAttribute("config", emailConfigService.getConfig());
        return "index";
    }

    @GetMapping("/config/update")
    public String updateConfig(Model model) {
        model.addAttribute("config", emailConfigService.getConfig());
        model.addAttribute("languages", emailConfigService.getLanguages());
        model.addAttribute("pageSizes", emailConfigService.getPageSizes());
        return "form";
    }

    @PostMapping("/config/update")
    public String updateConfigSubmit(@ModelAttribute EmailConfig config,
                                     RedirectAttributes redirectAttributes) {
        emailConfigService.updateConfig(config);
        redirectAttributes.addFlashAttribute("message", "Configuration updated successfully!");
        return "redirect:/config";
    }
}
