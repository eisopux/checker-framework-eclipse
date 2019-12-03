package lsp_checkerframework;

import java.io.File;
import java.net.URI;
import java.util.*;

import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.jsonrpc.messages.Message;
import org.eclipse.lsp4j.jsonrpc.messages.ResponseMessage;
import org.eclipse.lsp4j.services.LanguageServer;

public class LanguageServerStreamConnectionProvider extends ProcessStreamConnectionProvider {
	public LanguageServerStreamConnectionProvider() {
		System.out.println("Initializing LangServer");
		
		File javaLocation = getjavaLocation();
		
		List<String> commands = new ArrayList<>();
		commands.add(javaLocation.getAbsolutePath());
		commands.add("-cp");
		commands.add("/Users/michaeljiang/Documents/workspace/LSP_CheckerFramework/src/lsp_checkerframework/checker-framework-languageserver-0.0.6.jar:/Users/michaeljiang/Documents/checker/dist/checker.jar");
		commands.add("org.checkerframework.languageserver.ServerMain");
		commands.add("--frameworkPath");
		commands.add("/Users/michaeljiang/Documents/");
		commands.add("--checkers");
		commands.add("org.checkerframework.checker.nullness.NullnessChecker");

		setCommands(commands);
		setWorkingDirectory(System.getProperty("user.dir"));
	}

	@Override
	public Object getInitializationOptions(URI rootUri) {
		System.out.println("LangServer getInitializationOptions");
		Map<String, Object> options = new HashMap<>();
		return options;
	}

	@Override
	public void handleMessage(Message message, LanguageServer languageServer, URI rootUri) {
		System.out.printf("LangServer handleMessage:\t%s%n\t%s%n\t%s%n", message, languageServer, rootUri);
		if (message instanceof ResponseMessage) {
			ResponseMessage responseMessage = (ResponseMessage) message;
			if (responseMessage.getResult() instanceof InitializeResult) {
			}
		}
	}

	@Override
	public String toString() {
		return "Language Server: " + super.toString();
	}
	
	private static File getjavaLocation() {
		return new File ("/usr/bin/java");
	}
}