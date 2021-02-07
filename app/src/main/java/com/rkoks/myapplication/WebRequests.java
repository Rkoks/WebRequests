package com.rkoks.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebRequests {
	public static String getContent(String http) throws IOException {
		HttpURLConnection connection = null;
		String line = null;
		StringBuilder sb = new StringBuilder();
		try {
			connection = (HttpURLConnection) new URL(http).openConnection();

			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setConnectTimeout(1000);
			connection.setReadTimeout(1000);

			connection.connect();

			if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
				System.out.println("WebReqeust: " + line);

			} else {
				System.out.println("WebRequest: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
			}
		} catch (Throwable cause) {
			cause.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return sb.toString();
	}
}