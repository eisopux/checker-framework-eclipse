package lsp_checkerframework;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.lsp4e.server.StreamConnectionProvider;

public class LanguageServerConnectionProvider implements StreamConnectionProvider {
	
	private Process process;
	private DebugInputStream debugInputStream;
	private DebugOutputStream debugOutputStream;

	@Override
	public void start() throws IOException {
		System.out.println("Starting server");
		
		URL serverFileUrl = getClass().getResource("./checker-framework-languageserver-all.jar");	
		File serverFile = new File(FileLocator.toFileURL(serverFileUrl).getPath());
		File javaLocation = getjavaLocation();
		ProcessBuilder builder = new ProcessBuilder(
				javaLocation.getAbsolutePath(),
				"-cp",
				"/Users/michaeljiang/Documents/checker/dist/checker.jar:/Users/michaeljiang/Documents/workspace/LSP_CheckerFramework/src/lsp_checkerframework/checker-framework-languageserver-all.jar", 
				"org.checkerframework.languageserver.ServerMain"
		);
		
		process = builder.start();
		
		if (!process.isAlive()) {
			throw new IOException("Unable to start language server: " + this.toString());
		}
		
		System.out.println("Started Process");
	}

	@Override
	public InputStream getInputStream() {
//		InputStream input = process.getInputStream();
		debugInputStream = new DebugInputStream(process.getInputStream(), true);
//		System.out.println(input.toString());
		
		return debugInputStream;
	}

	@Override
	public OutputStream getOutputStream() {
//		OutputStream output = process.getOutputStream();
		debugOutputStream = new DebugOutputStream(process.getOutputStream(), true);
//		System.out.println(output.toString());
		return debugOutputStream;
	}

	@Override
	public InputStream getErrorStream() {
		InputStream error = process.getErrorStream();
		System.out.println(error.toString());
		return error;
	}

	@Override
	public void stop() {
		System.out.println("Stopping language server");
		process.destroy();
		process = null;
		
	}
	
	private static File getjavaLocation() {
		return new File ("/usr/bin/java");
	}

}

