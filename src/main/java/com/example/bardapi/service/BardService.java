package com.example.bardapi.service;

import com.example.bardapi.domain.BardAnswer;
import com.example.bardapi.domain.BardQuestion;
import com.example.bardapi.domain.LandNews;
import com.example.bardapi.domain.News;
import com.example.bardapi.utils.MongoQueryUtil;
import com.example.bardapi.utils.SummaryUtil;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import com.pkslow.ai.domain.AnswerStatus;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * PackageName 	: com.example.bardapi.service
 * FileName 	: BardService
 * Author 		: jhchoi
 * Date 		: 2023-12-27
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-12-27			jhchoi				최초 생성
 */
@Service
public class BardService {
	// Initial Bard API Client
	private GoogleBardClient client;
	private MongoTemplate mongoTemplate;

	public BardService(GoogleBardClient client, MongoTemplate mongoTemplate) {
		this.client = client;
		this.mongoTemplate = mongoTemplate;
	}

	public BardAnswer askBard(BardQuestion question) {
		return ask(question.getQuestion(), question.getToken());
	}

	public void summaryBard(BardQuestion question) {
		List<LandNews> landNewsList = mongoTemplate.find(MongoQueryUtil.todayNewsSearch(), LandNews.class);
		for (int i = 0; i < landNewsList.size(); i++) {
			if (landNewsList.get(i).getSummary().isEmpty()) {
				System.out.println("total : " + landNewsList.size() + " ---> summary processing = " + i + " [" +landNewsList.get(i).getNewsId() + "]");
				String contents = SummaryUtil.ofSummary(landNewsList.get(i));
				if (contents != null) {
					LandNews newLandNews = LandNews.of(landNewsList.get(i), ask(contents, question.getToken()).getChosenAnswer());
					mongoTemplate.save(newLandNews);
				}
			}
		}
		System.out.println("Summary Processing Complete!!");
	}

	public void summaryOneBard(BardQuestion question) {
		LandNews landNews = mongoTemplate.findOne(MongoQueryUtil.findOne(question.getNewsId()), LandNews.class);
		String contents = SummaryUtil.ofSummary(landNews);
		if (contents != null) {
			LandNews newLandNews = LandNews.of(landNews, ask(contents, question.getToken()).getChosenAnswer());
			mongoTemplate.save(newLandNews);
		}
		System.out.println(landNews.getNewsId() + " --> Summary Processing Complete!!");
	}

	public List<News> getSummaryNewsList() {
		List<News> resultList = new ArrayList<>();
		for (LandNews landNews : mongoTemplate.find(MongoQueryUtil.todayNewsSummarySearch(), LandNews.class)) {
			resultList.add(News.of(String.valueOf(landNews.getNewsId()), landNews.getTitle(), landNews.getUrl(), landNews.getSummary()));
		}
		return resultList;
	}

	public List<News> getNewsList() {
		List<News> resultList = new ArrayList<>();
		for (LandNews landNews : mongoTemplate.find(MongoQueryUtil.todayNewsSearch(), LandNews.class)) {
			resultList.add(News.of(String.valueOf(landNews.getNewsId()), landNews.getTitle(), landNews.getUrl(), landNews.getSummary()));
		}
		return resultList;
	}

	public List<News> getSearchTitle(BardQuestion question) {
		List<News> resultList = new ArrayList<>();
		for (LandNews landNews : mongoTemplate.find(MongoQueryUtil.todaySearchTitle(question.getTitle()), LandNews.class)) {
			resultList.add(News.of(String.valueOf(landNews.getNewsId()), landNews.getTitle(), landNews.getUrl(), landNews.getSummary()));
		}
		return resultList;
	}

	public List<String> getAllTitle() {
		List<String> resultList = new ArrayList<>();
		for (LandNews landNews : mongoTemplate.find(MongoQueryUtil.todayNewsTitleSearch(), LandNews.class)) {
			resultList.add(landNews.getTitle());
		}
		return resultList;
	}

	private BardAnswer ask(String question, String token) {
		Answer answer = client.ask(question);
		if (!answer.getStatus().equals(AnswerStatus.OK)) {
			client = new GoogleBardClient(token);
			answer = client.ask(question);
		}
		if (answer.getStatus() == AnswerStatus.OK) {
			return new BardAnswer(answer.getChosenAnswer());
		}

		throw new RuntimeException("Can't access to Google Bard");
	}

}
