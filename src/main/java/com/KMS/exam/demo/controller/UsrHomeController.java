package com.KMS.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	int count;
	
	public UsrHomeController(){
		count = 0;
	}
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요";
	}
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "반갑습니다.";
	} 
	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public String showMain3() {
		return "잘가요";
	}
	@RequestMapping("/usr/home/getCount")
	@ResponseBody
	public int showMain4() {
		return count++;
	}
	@RequestMapping("/usr/home/doSetCount")
	@ResponseBody
	public String doSetCount(int Count) {
		this.count = Count;
		return "카운트 값이 "+ count+"으로 초기화 됨";
	}
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map getMap() {
		Map<String,Object> map = new HashMap<>();
		map.put("철수나이",22);
		map.put("영희나이",22);
		return map;
	}
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		list.add("철수나이");
		list.add("영희나이");
		return list;
	}
	
}
