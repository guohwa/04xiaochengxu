package com.example.miniprogram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import com.example.miniprogram.util.LunarConverter;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/data")
    public String getData() {
        return "小程序数据请求成功!";
    }
    
    /**
     * 将公历日期转换为农历日期
     * @param requestBody 包含日期和时辰的请求体
     * @return 转换后的农历日期
     */
    @PostMapping("/lunar")
    public ResponseEntity<Map<String, Object>> convertToLunar(@RequestBody Map<String, String> requestBody) {
        try {
            String date = requestBody.get("date");
            String hour = requestBody.get("hour");
            
            if (date == null || date.isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "日期不能为空");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // 调用转换工具类
            String lunarDate = LunarConverter.convertToLunar(date, hour);
            
            Map<String, Object> response = new HashMap<>();
            response.put("lunar", lunarDate);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "转换失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}