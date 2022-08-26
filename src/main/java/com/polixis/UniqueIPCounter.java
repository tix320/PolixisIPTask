package com.polixis;

import java.util.Iterator;

public class UniqueIPCounter {

	private static final int BITSET_ARRAY_LENGTH = 256 * 256 * 256 * 32;

	private static final int FIRST_LEVEL_BUCKET_SIZE = BITSET_ARRAY_LENGTH / 256;
	private static final int SECOND_LEVEL_BUCKET_SIZE = FIRST_LEVEL_BUCKET_SIZE / 256;
	private static final int THIRD_LEVEL_BUCKET_SIZE = SECOND_LEVEL_BUCKET_SIZE / 256;

	private final Iterable<String> source;

	public UniqueIPCounter(Iterable<String> source) {
		this.source = source;
	}

	public long getUniqueIPCount() {
		byte[] bitset = new byte[BITSET_ARRAY_LENGTH];

		Iterator<String> iterator = source.iterator();

		long uniqueIPsCount = 0;

		while (iterator.hasNext()) {
			String next = iterator.next();
			int[] ip = parseIP(next);

			int index = ip[0] * FIRST_LEVEL_BUCKET_SIZE + ip[1] * SECOND_LEVEL_BUCKET_SIZE + ip[2] * THIRD_LEVEL_BUCKET_SIZE + ip[3] / 8;

			int bitPosition = ip[3] % 8;
			int mask = 1 << bitPosition;
			if ((bitset[index] & mask) == 0) {
				uniqueIPsCount++;
				bitset[index] |= mask;
			}
		}

		return uniqueIPsCount;
	}

	private static int[] parseIP(String str) {
		int[] data = new int[4];

		// splitting was written manually, because String.split() is slow (internally used regex)
		int previousIndex = -1;
		for (int i = 0; i < 4; i++) {
			int currentIndex = str.indexOf('.', previousIndex + 1);
			String substring = str.substring(previousIndex + 1, currentIndex == -1 ? str.length() : currentIndex);
			int part = Integer.parseInt(substring);
			if (part < 0 || part > 255) {
				throw new IllegalArgumentException("IP part must be in range [0,255], but was %s".formatted(part));
			}
			data[i] = part;
			previousIndex = currentIndex;
		}

		return data;
	}
}
