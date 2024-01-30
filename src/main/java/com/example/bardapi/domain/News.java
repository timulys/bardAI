package com.example.bardapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PackageName 	: com.example.bardapi.domain
 * FileName 	: News
 * Author 		: jhchoi
 * Date 		: 2023-12-27
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-12-27			jhchoi				최초 생성
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class News {
	private String newsId;
	private String title;
	private String url;
	private String summary;

	public static News of(String newsId, String title, String url, String summary) {
		return new News(newsId, title, url, summary);
	}
}
