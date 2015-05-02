package com.mmm.greenway.data.service;

import java.util.Random;

public class StringRandomizer {

	private static String charsStr = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
	private static Random random = new Random();

	public static String getRandomStringValue(int lenght) {
		StringBuilder sb = new StringBuilder();
		int charsCount = charsStr.length();
		char[] chars = charsStr.toCharArray();
		for (int i = 0; i < lenght; i++) {
			sb.append(chars[random.nextInt(charsCount)]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		for (int i = 4; i < 10; i++) {
			System.out.println(getRandomStringValue(i));
		}
	}
}
