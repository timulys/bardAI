package com.example.bardapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * PackageName 	: com.example.bardapi.controller
 * FileName 	: IndexController
 * Author 		: jhchoi
 * Date 		: 2023-12-27
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-12-27			jhchoi				최초 생성
 */
@Controller
public class IndexController {
	@GetMapping(value = "/")
	public String getIndex() {
		return "index";
	}
}
