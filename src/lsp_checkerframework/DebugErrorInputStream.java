package lsp_checkerframework;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;

public class DebugErrorInputStream extends InputStream {
	InputStream is;
	boolean debug;

	public DebugErrorInputStream(InputStream in, boolean usedebug) {
		is = in;
		debug = usedebug;
	}

	public int read() throws IOException {
		int input;

		input = is.read();
		if (debug) System.out.println("ErrorStream Input: " + (char)input);

		return input;
	}

	public int read(byte b[], int off, int len) throws IOException {
		int readb;

		readb = is.read(b, off, len);
		if (debug) {
			System.out.print("ErrorStream Input byte array: ");
			for (byte e : b) System.out.println((char)e + " ");
			System.out.println("\t with offset: " + off + " with length: " + len);
		}
		return readb;
	}

	public int available() throws IOException {
		return is.available();
	}

	public void close() throws IOException {
		is.close();
	}

	public void mark(int readlimit) {
		is.mark(readlimit);
	}

	public void reset() throws IOException {
		is.reset();
	}

	public boolean markSupported() {
		return is.markSupported();
	}
}
