package com.example.accountbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//このクラスが設定用クラスであることをspringに伝える
@Configuration
public class WebConfig {
    //コンテナ内でこのオブジェクトを管理可能なbeanを登録する
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("PUT", "POST", "PUT", "DELETE")
                        .allowCredentials(true);
            }
        };
    }
}
// CORS：クロスオリジンリソース共有
//VueフロンドエンドとSpringBootバックエンドが異なるドメインとポートで実行されることが一般的です
//ブラウザはセキュリティーの観点から異なるオリジン間のリクエストを制限する、
// CORS設定を利用することで、特定のオリジンからアクセスを許可することができます