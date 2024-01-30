package com.example.bardapi.controller.rest;

import com.example.bardapi.domain.BardAnswer;
import com.example.bardapi.domain.BardQuestion;
import com.example.bardapi.domain.News;
import com.example.bardapi.service.BardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PackageName 	: com.example.bardapi.controller
 * FileName 	: BardController
 * Author 		: jhchoi
 * Date 		: 2023-12-27
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-12-27			jhchoi				최초 생성
 */
@RestController
@RequestMapping("/google-bard")
@RequiredArgsConstructor
public class BardController {
	// Bard API Service
	private final BardService service;

	@PostMapping("/ask")
	public ResponseEntity<BardAnswer> ask(@RequestBody BardQuestion question) {
		return ResponseEntity.ok(service.askBard(question));
	}

	@PostMapping("/summary")
	public ResponseEntity summary(@RequestBody BardQuestion question) {
		service.summaryBard(question);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/summary/one")
	public ResponseEntity summaryOne(BardQuestion question) {
		service.summaryOneBard(question);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/summary/all")
	public ResponseEntity<List<News>> getSummaryNewsList() {
		return ResponseEntity.ok(service.getSummaryNewsList());
	}

	@GetMapping("/news")
	public ResponseEntity<List<News>> getNewsList() {
		return ResponseEntity.ok(service.getNewsList());
	}

	@GetMapping("/search/title")
	public ResponseEntity<List<News>> getSearchTitle(BardQuestion question) {
		return ResponseEntity.ok(service.getSearchTitle(question));
	}

	@GetMapping("/search/all-title")
	public ResponseEntity<List<String>> getAllTitle() {
		return ResponseEntity.ok(service.getAllTitle());
	}

	@GetMapping("/search/head-line")
	public ResponseEntity<List<News>> getHeadLine() {
		return ResponseEntity.ok(service.getNewsList());
	}
}
