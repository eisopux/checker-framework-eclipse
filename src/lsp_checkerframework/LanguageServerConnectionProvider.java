package lsp_checkerframework;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.lsp4e.server.StreamConnectionProvider;

public class LanguageServerConnectionProvider implements StreamConnectionProvider {
	
	private Process process;

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
		InputStream input = process.getInputStream();
		System.out.println(input.toString());
		return input;
	}

	@Override
	public OutputStream getOutputStream() {
		OutputStream output = process.getOutputStream();
		System.out.println(output.toString());
		return output;
	}

	@Override
	public InputStream getErrorStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stop() {
		process.destroy();
		process = null;
		
	}
	
	private static File getjavaLocation() {
		return new File ("/usr/bin/java");
	}

}

