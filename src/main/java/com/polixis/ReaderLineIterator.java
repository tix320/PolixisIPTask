package com.polixis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReaderLineIterator implements Iterator<String> {

	private static final int DEFAULT_BUFFER_SIZE = 1000;

	private final BufferedReader bufferedReader;

	private final String[] buffer;
	private int bufferIndex;

	public ReaderLineIterator(Reader reader, int bufferSize) throws FileNotFoundException {
		if (bufferSize <= 0) {
			throw new IllegalArgumentException("" + bufferSize);
		}

		this.bufferedReader = new BufferedReader(reader);
		this.buffer = new String[bufferSize];
		this.bufferIndex = 0;
	}

	public ReaderLineIterator(Reader reader) throws FileNotFoundException {
		this(reader, DEFAULT_BUFFER_SIZE);
	}

	@Override
	public boolean hasNext() {
		if (buffer[bufferIndex] != null) {
			return true;
		}

		resetBuffer();

		return buffer[0] != null;
	}

	@Override
	public String next() {
		String item = buffer[bufferIndex++];
		if (item == null) {
			throw new NoSuchElementException();
		}

		if (bufferIndex == buffer.length) {
			resetBuffer();
		}

		return item;
	}

	private void resetBuffer() {
		for (int i = 0; i < buffer.length; i++) {
			String line;
			try {
				line = bufferedReader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			buffer[i] = line;

			if (line == null) {
				break;
			}
		}

		bufferIndex = 0;
	}
}
