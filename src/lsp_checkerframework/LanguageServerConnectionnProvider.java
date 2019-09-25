package lsp_checkerframework;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.lsp4e.server.StreamConnectionProvider;

public class LanguageServerConnectionnProvider implements StreamConnectionProvider {
	
	private Process process;

	@Override
	public void start() throws IOException {
		URL serverFileUrl = getClass().getResource("");	//"/server/languageserver/server.js"
		File serverFile = new File(FileLocator.toFileURL(serverFileUrl).getPath());
		File nodeJsLocation = getNodeJsLocation();
		ProcessBuilder builder = new ProcessBuilder(
				nodeJsLocation.getAbsolutePath(),
				serverFile.getAbsolutePath());
		
		process = builder.start();
		
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return process.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return process.getOutputStream();
	}

	@Override
	public @Nullable InputStream getErrorStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stop() {
		process.destroy();
		process = null;
		
	}
	
	private static File getNodeJsLocation() {
		return new File ("/usr/bin/node");
	}

}
