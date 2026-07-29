package com.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class DictionaryController {
    private static final Map<String, String> dictionary = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        dictionary.put("hello", "Xin chào");
        dictionary.put("goodbye", "Tạm biệt");
        dictionary.put("thank you", "Cảm ơn");
        dictionary.put("yes", "Có");
        dictionary.put("no", "Không");
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("dictionary", dictionary.keySet());
        return "result";
    }

    /**
     * Xử lý tra cứu từ vựng (hỗ trợ cả GET và POST)
     */
    @RequestMapping(value = "/lookup", method = {RequestMethod.GET, RequestMethod.POST})
    public String lookup(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("dictionaryKeys", dictionary.keySet());

        if (keyword != null && !keyword.trim().isEmpty()) {
            String searchWord = keyword.trim();
            String meaning = dictionary.get(searchWord);

            model.addAttribute("keyword", searchWord);
            if (meaning != null) {
                // Trường hợp TÌM THẤY từ vựng
                model.addAttribute("found", true);
                model.addAttribute("meaning", meaning);
            } else {
                // Trường hợp KHÔNG TÌM THẤY từ vựng
                model.addAttribute("found", false);
                model.addAttribute("message", "Không tìm thấy từ \"" + searchWord + "\" trong từ điển.");
            }
        }
        return "result";
    }
}
