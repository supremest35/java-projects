package kr.co.hta.school.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Keyboard {

	private static BufferedReader reader = null;
	
	static {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private Keyboard() {}
	
	public static int nextInt() {
		try {
			return Integer.parseInt(reader.readLine());
		} catch (IOException | NumberFormatException e) {
			return 0;
		}
	}

	public static long nextLong() {
		try {
			return Long.parseLong(reader.readLine());
		} catch (IOException | NumberFormatException e) {
			return 0L;
		}
	}

	public static double nextDouble() {
		try {
			return Double.parseDouble(reader.readLine());
		} catch (IOException | NumberFormatException e) {
			return 0.0;
		}
	}

	public static String nextString() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			return null;
		}
	}
}
