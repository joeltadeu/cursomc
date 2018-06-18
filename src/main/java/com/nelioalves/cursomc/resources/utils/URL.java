package com.nelioalves.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	public static List<Integer> decodeIntList(String ids) {
		return Arrays.asList(ids.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
