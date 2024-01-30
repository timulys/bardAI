package com.example.bardapi.config;

import com.pkslow.ai.GoogleBardClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * PackageName 	: com.example.bardapi.config
 * FileName 	: GoogleBardConfig
 * Author 		: jhchoi
 * Date 		: 2023-12-27
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-12-27			jhchoi				최초 생성
 */
@Configuration
public class GoogleBardConfig {
	@Bean
	public GoogleBardClient googleBardClient(@Value("${ai.google-bard.token}") String token) {
		return new GoogleBardClient(token);
	}
}
