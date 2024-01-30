package com.example.bardapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * PackageName 	: com.example.bardapi.domain
 * FileName 	: LandNews
 * Author 		: jhchoi
 * Date 		: 2023-12-27
 * Description 	: Mongo DB Select Land News
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-12-27			jhchoi				최초 생성
 */
@Getter
@Document(collection = "land")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LandNews {
	@Id
	private Long newsId;
	private String url;
	private String title;
	private Long newsDate;
	private String summary;

	public static LandNews of(LandNews landNews, String summary) {
		return new LandNews(landNews.getNewsId(), landNews.getUrl(), landNews.getTitle(), landNews.newsDate, summary);
	}
}
