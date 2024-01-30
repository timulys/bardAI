package com.example.bardapi.domain;

import lombok.Data;

/**
 * PackageName 	: com.example.bardapi.domain
 * FileName 	: BardQuestion
 * Author 		: jhchoi
 * Date 		: 2023-12-27
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-12-27			jhchoi				최초 생성
 */
@Data
public class BardQuestion {
	private String newsId;
	private String question;
	private String title;
	private String token;
}
