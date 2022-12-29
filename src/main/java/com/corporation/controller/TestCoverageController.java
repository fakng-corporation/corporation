package com.corporation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/testcoverage")
public class TestCoverageController {

    @GetMapping
    public String testCoverageInfo () {
        return "redirect:/jacocoHtml/index.html";
    }
}