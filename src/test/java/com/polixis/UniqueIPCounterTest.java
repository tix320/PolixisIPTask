package com.polixis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqueIPCounterTest {

	@Test
	void smallSequentialTest() {
		List<String> IPs = generateSequentialIPs(100);
		UniqueIPCounter uniqueIPCounter = new UniqueIPCounter(IPs);

		assertEquals(new HashSet<>(IPs).size(), uniqueIPCounter.getUniqueIPCount());
	}

	@Test
	void mediumSequentialTest() {
		List<String> IPs = generateSequentialIPs(10000);
		UniqueIPCounter uniqueIPCounter = new UniqueIPCounter(IPs);

		assertEquals(new HashSet<>(IPs).size(), uniqueIPCounter.getUniqueIPCount());
	}

	@Test
	void largeSequentialTest() {
		List<String> IPs = generateSequentialIPs(1000000);
		UniqueIPCounter uniqueIPCounter = new UniqueIPCounter(IPs);

		assertEquals(new HashSet<>(IPs).size(), uniqueIPCounter.getUniqueIPCount());
	}

	@Test
	void smallRandomTest() {
		List<String> randomIPs = generateRandomIPs(100);
		UniqueIPCounter uniqueIPCounter = new UniqueIPCounter(randomIPs);

		assertEquals(new HashSet<>(randomIPs).size(), uniqueIPCounter.getUniqueIPCount());
	}

	@Test
	void mediumRandomTest() {
		List<String> randomIPs = generateRandomIPs(10000);
		UniqueIPCounter uniqueIPCounter = new UniqueIPCounter(randomIPs);

		assertEquals(new HashSet<>(randomIPs).size(), uniqueIPCounter.getUniqueIPCount());
	}

	@Test
	void largeRandomTest() throws InterruptedException {
		List<String> randomIPs = generateRandomIPs(1000000);
		UniqueIPCounter uniqueIPCounter = new UniqueIPCounter(randomIPs);

		assertEquals(new HashSet<>(randomIPs).size(), uniqueIPCounter.getUniqueIPCount());
	}

	private static List<String> generateSequentialIPs(int count) {
		List<String> IPs = new ArrayList<>(count);

		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		for (int i = 0; i < count; i++) {
			if (d > 255) {
				d = 0;
				c++;
			}

			if (c > 255) {
				c = 0;
				b++;
			}

			if (b > 255) {
				b = 0;
				a++;
			}

			IPs.add(a + "." + b + "." + c + "." + d);

			d++;
		}

		return IPs;
	}

	private static List<String> generateRandomIPs(int count) {
		return Stream.generate(UniqueIPCounterTest::createRandomIP).limit(count).toList();
	}

	private static String createRandomIP() {
		return randomNumber() + "." + randomNumber() + "." + randomNumber() + "." + randomNumber();
	}

	private static int randomNumber() {
		return new Random().nextInt((255 - 1) + 1) + 1;
	}
}
