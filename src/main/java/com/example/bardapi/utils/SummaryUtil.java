package com.example.bardapi.utils;

import com.example.bardapi.domain.LandNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * PackageName 	: com.example.bardapi.utils
 * FileName 	: Summary
 * Author 		: jhchoi
 * Date 		: 2023-12-27
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-12-27			jhchoi				최초 생성
 */
@Component
public class SummaryUtil {
	public static String ofSummary(LandNews news) {
		String content = "";
		try {
			Document doc = Jsoup.connect(news.getUrl()).get();
			Elements contents = doc.getElementsByClass("article_view").get(0).getElementsByTag("p");
			if (contents.size() > 3) {
				for (Element el : contents) {
					content += el.text();
				}
				content += " 이 내용을 딱 다섯 줄로 요약해줘.";
				System.out.println(news.getUrl() + " - Processing summary news...");
			} else {
				content = null;
				System.out.println(news.getUrl() + " - Too short news...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
