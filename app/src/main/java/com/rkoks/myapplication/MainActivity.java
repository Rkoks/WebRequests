package com.rkoks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final String[] answer = new String[1];
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					answer[0] = WebRequests.getContent("https://www.google.ru/");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TextView textView = (TextView) findViewById(R.id.text1);
		textView.setText(answer[0]);

	}
}