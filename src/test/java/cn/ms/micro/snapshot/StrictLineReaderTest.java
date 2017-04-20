package cn.ms.micro.snapshot;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class StrictLineReaderTest {
	@Test
	public void lineReaderConsistencyWithReadAsciiLine() {
		try {
			// Testing with LineReader buffer capacity 32 to check some corner
			// cases.
			StrictLineReader lineReader = new StrictLineReader(createTestInputStream(), 32, DiskLruCacheSupport.US_ASCII);
			InputStream refStream = createTestInputStream();
			while (true) {
				try {
					String refLine = readAsciiLine(refStream);
					try {
						String line = lineReader.readLine();
						if (!refLine.equals(line)) {
							fail("line (\"" + line + "\") differs from expected (\"" + refLine + "\").");
						}
					} catch (EOFException eof) {
						fail("line reader threw EOFException too early.");
					}
				} catch (EOFException refEof) {
					try {
						lineReader.readLine();
						fail("line reader didn't throw the expected EOFException.");
					} catch (EOFException expected) {
						break;
					}
				}
			}
			refStream.close();
			lineReader.close();
		} catch (IOException ioe) {
			fail("Unexpected IOException " + ioe.toString());
		}
	}

	/* XXX From libcore.io.Streams */
	private static String readAsciiLine(InputStream in) throws IOException {
		// TODO: support UTF-8 here instead

		StringBuilder result = new StringBuilder(80);
		while (true) {
			int c = in.read();
			if (c == -1) {
				throw new EOFException();
			} else if (c == '\n') {
				break;
			}

			result.append((char) c);
		}
		int length = result.length();
		if (length > 0 && result.charAt(length - 1) == '\r') {
			result.setLength(length - 1);
		}
		return result.toString();
	}

	private static InputStream createTestInputStream() {
		return new ByteArrayInputStream(
				(""
						// Each source lines below should represent 32 bytes, until the next comment.
						+ "12 byte line\n18 byte line......\n"
						+ "pad\nline spanning two 32-byte bu"
						+ "ffers\npad......................\n"
						+ "pad\nline spanning three 32-byte "
						+ "buffers and ending with LF at th"
						+ "e end of a 32 byte buffer......\n"
						+ "pad\nLine ending with CRLF split"
						+ " at the end of a 32-byte buffer\r"
						+ "\npad...........................\n"
						// End of 32-byte lines.
						+ "line ending with CRLF\r\n"
						+ "this is a long line with embedded CR \r ending with CRLF and having more than "
						+ "32 characters\r\n"
						+ "unterminated line - should be dropped").getBytes());
	}
}
