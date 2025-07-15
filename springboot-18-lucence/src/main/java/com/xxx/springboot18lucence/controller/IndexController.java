package com.xxx.springboot18lucence.controller;

import com.xxx.springboot18lucence.lucene.ChineseSearcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/lucene")
public class IndexController {
    @GetMapping("/query/cn")
    public String queryCN(String keyword, Model model) {
        String indexDir = "D:\\spirngboot-tutorials\\springboot-18-lucence\\luceneCN";
        //String keyword = "南京文明";
//        String keyword = "南京文化";

        try {
            List<String> list = ChineseSearcher.search(indexDir, keyword);
            model.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }
}
