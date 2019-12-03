package lsp_checkerframework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DebugOutputStream extends OutputStream {
	private OutputStream out;
	private boolean debug;
	
	public DebugOutputStream(OutputStream o, boolean usedebug) {
		out = o;
		debug = usedebug;
	}

	@Override
	public void write(int b) throws IOException {
		if (debug) System.out.print((char)b);

		out.write(b);
	}

	public void close() throws IOException {
		out.close();
	}
	
	public void flush() throws IOException {
		out.flush();
    }
}
