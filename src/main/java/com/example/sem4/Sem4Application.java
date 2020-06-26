package com.example.sem4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sem4Application {

//    @Value("${cloudinary.cloud_name}")
//    private String cloudName="dcwm5m9ml";
//
//    @Value("${cloudinary.api_key}")
//    private String apiKey="937311953352482";
//
//    @Value("${cloudinary.api_secret}")
//    private String apiSecret="9mLyF9v1ern4zseavbUrmGSPlhQ";

    public static void main(String[] args) {
        SpringApplication.run(Sem4Application.class, args);

    }

//    @Bean
//    public Cloudinary cloudinaryConfig() {
//        Cloudinary cloudinary = null;
//        Map config = new HashMap();
//        config.put("cloud_name", cloudName);
//        config.put("api_key", apiKey);
//        config.put("api_secret", apiSecret);
//        cloudinary = new Cloudinary(config);
//        return cloudinary;
//    }

}
