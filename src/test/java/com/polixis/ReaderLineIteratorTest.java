package com.polixis;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderLineIteratorTest {

	@Test
	public void simpleTest() throws FileNotFoundException {
		ReaderLineIterator readerLineIterator = new ReaderLineIterator(
				new InputStreamReader(new ByteArrayInputStream("""
						aaa
						bbb
						ccc
						ddd
						ee
						""".getBytes(StandardCharsets.UTF_8))), 2);

		assertTrue(readerLineIterator.hasNext());
		assertEquals("aaa", readerLineIterator.next());
		assertEquals("bbb", readerLineIterator.next());
		assertTrue(readerLineIterator.hasNext());
		assertEquals("ccc", readerLineIterator.next());
		assertEquals("ddd", readerLineIterator.next());
		assertEquals("ee", readerLineIterator.next());
		assertFalse(readerLineIterator.hasNext());

		assertThrows(NoSuchElementException.class, readerLineIterator::next);
		assertThrows(NoSuchElementException.class, readerLineIterator::next);
	}
}
