package com.example.miniprogram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Value("${wechat.appid}")
private String appid;

@Value("${wechat.secret}")
private String secret;

@PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
    String code = body.get("code");
    String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid 
        + "&secret=" + secret 
        + "&js_code=" + code 
        + "&grant_type=authorization_code";

    try {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        
        if (response.containsKey("errcode")) {
            return ResponseEntity.badRequest().body(Map.of(
                "code", 400,
                "message", "微信验证失败: " + response.get("errmsg")
            ));
        }
        
        return ResponseEntity.ok(Map.of(
            "code", 200,
            "data", Map.of(
                "openid", response.get("openid"),
                "session_key", response.get("session_key")
            )
        ));
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body(Map.of(
            "code", 500,
            "message", "服务器异常: " + e.getMessage()
        ));
    }
}