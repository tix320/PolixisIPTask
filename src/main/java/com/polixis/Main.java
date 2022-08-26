package com.polixis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Main {

	private static final int BUFFER_SIZE = 1000;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please provide file path");
		}

		File file = new File(args[0]);

		if (!file.exists()) {
			System.err.println("Please provide existing file path");
		}

		Iterable<String> iterable = () -> {
			try {
				return new ReaderLineIterator(new InputStreamReader(new FileInputStream(file)), BUFFER_SIZE);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		};

		UniqueIPCounter uniqueIPCounter = new UniqueIPCounter(iterable);

		System.out.printf("Unique IPs count: %s%n", uniqueIPCounter.getUniqueIPCount());
	}
}
