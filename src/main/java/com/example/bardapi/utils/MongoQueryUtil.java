package com.example.bardapi.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * PackageName 	: com.example.newspipeline.utils
 * FileName 	: MongoUtil
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
public class MongoQueryUtil {
	/**
	 * Mongo DB Default Find Query
	 * with Sort and limit 10
	 * @return
	 */
	public static Query defaultSearch(int limit) {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "newsId")).limit(limit);
		return query;
	}

	public static Query findOne(String newsId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("url").regex(newsId));
		return query;
	}

	public static Query todayNewsSummarySearch() {
		Query query = getToday();
		query.addCriteria(Criteria.where("summary").ne(""));
		query.with(Sort.by(Sort.Direction.DESC, "newsId"));
		return query;
	}

	public static Query todayNewsSearch() {
		Query query = getToday();
		query.addCriteria(Criteria.where("summary").regex(""));
		query.with(Sort.by(Sort.Direction.DESC, "newsId"));
		return query;
	}

	public static Query todayNewsTitleSearch() {
		Query query = getToday();
		query.with(Sort.by(Sort.Direction.DESC, "newsId"));
		return query;
	}

	public static Query todaySearchTitle(String title) {
		Query query = getToday();
		query.addCriteria(Criteria.where("title").regex(title));
		return query;
	}

	@NotNull
	private static Query getToday() {
		LocalDate today = LocalDate.now();
		Query query = new Query();
		query.addCriteria(Criteria.where("url")
			.regex(String.valueOf(today.getYear()) +
				String.valueOf(today.getMonthValue() < 10 ? "0" + today.getMonthValue() : today.getMonthValue()) +
				String.valueOf(today.getDayOfMonth() < 10 ? "0" + today.getDayOfMonth() : today.getDayOfMonth())));
		return query;
	}
}
