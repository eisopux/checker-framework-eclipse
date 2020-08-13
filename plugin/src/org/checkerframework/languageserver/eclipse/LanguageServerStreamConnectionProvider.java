package org.checkerframework.languageserver.eclipse;

import java.io.File;
import java.net.URI;
import java.util.*;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.jsonrpc.messages.Message;
import org.eclipse.lsp4j.jsonrpc.messages.ResponseMessage;
import org.eclipse.lsp4j.services.LanguageServer;

public class LanguageServerStreamConnectionProvider extends ProcessStreamConnectionProvider {
  public LanguageServerStreamConnectionProvider() {

    /** Get the preferences from preference page */
    IPreferenceStore store = LSPCheckerFrameworkPlugin.getDefault().getPreferenceStore();

    String typeChecker = store.getString(LSPCheckerFrameworkConstants.TYPE_CHECKER);
    String checkerPath = store.getString(LSPCheckerFrameworkConstants.CHECKER_PATH);
    String commandOptions = store.getString(LSPCheckerFrameworkConstants.COMMAND_OPTIONS);

    assert (!typeChecker.isEmpty());
    assert (!checkerPath.isEmpty());

    System.out.println("Type Checker: " + typeChecker);
    System.out.println("Checker Path: " + checkerPath);
    System.out.println("Command Options: " + commandOptions);

    /** Download checker framework and language server if needed */
    File f = new File(checkerPath);
    File[] listOfFiles = f.listFiles();

    if (listOfFiles == null) System.err.println("Error: checker path is invalid");

    boolean languageServerExists = false;
    boolean checkerFrameworkExists = false;

    for (File file : listOfFiles) {
      String name = file.getName();
      if (name.contains("checker-framework-languageserver") && name.contains(".jar")) {
        languageServerExists = true;
      } else if (name.contains("checker-framework")
          && !name.contains("languageserver")
          && !name.contains(".zip")) {
        checkerFrameworkExists = true;
      }
    }

    if (!languageServerExists || !checkerFrameworkExists) {
      // run the downloader
      try {
        final File downloaderFolder =
            new File(
                LanguageServerStreamConnectionProvider.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath());

        String downloaderPath =
            downloaderFolder.getAbsolutePath()
                + "/checker-framework-languageserver-downloader-0.1.0.jar";

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", downloaderPath, checkerPath);
        System.out.println("Executing Downloader...");
        Process p = pb.start(); // Start the process.
        int rc = p.waitFor(); // Wait for the process to finish.
        System.out.println("Downloader executed with exit code: " + rc);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    /** Start the language server */

    // get the paths of checker framework and language server
    f = new File(checkerPath);
    listOfFiles = f.listFiles();

    String checkerFrameworkAbsolutePath = "";
    String checkerFrameworkAbsolutePathFolder = "";
    String languageServerAbsolutePath = "";

    for (File file : listOfFiles) {
      String name = file.getName();

      if (name.contains("checker-framework-languageserver") && name.contains(".jar") && !name.contains("downloader")) {
        languageServerAbsolutePath = checkerPath + name;
      } else if (name.contains("checker-framework")
          && !name.contains("languageserver")
          && !name.contains(".zip")) {
        checkerFrameworkAbsolutePath = checkerPath + name + "/checker/dist/checker.jar";
        checkerFrameworkAbsolutePathFolder = checkerPath + name;
      }
    }

    System.out.println("Checker Framework Path: " + checkerFrameworkAbsolutePath);
    System.out.println("Language Server Path: " + languageServerAbsolutePath);

    System.out.println("Initializing LangServer");
    File javaLocation = getjavaLocation();

    List<String> commands = new ArrayList<>();
    commands.add(javaLocation.getAbsolutePath());
    commands.add("-cp");
    commands.add(languageServerAbsolutePath + ":" + checkerFrameworkAbsolutePath);
    commands.add("org.checkerframework.languageserver.ServerMain");
    commands.add("--frameworkPath");
    commands.add(checkerFrameworkAbsolutePathFolder);
    commands.add("--checkers");
    commands.add(typeChecker);

    if (!commandOptions.isEmpty()) {
      commands.add("--commandLineOptions");
      commands.add(commandOptions);
    }
    
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
    System.out.printf(
        "LangServer handleMessage:\t%s%n\t%s%n\t%s%n", message, languageServer, rootUri);
    if (message instanceof ResponseMessage) {
      ResponseMessage responseMessage = (ResponseMessage) message;
      if (responseMessage.getResult() instanceof InitializeResult) {}
    }
  }

  @Override
  public String toString() {
    return "Language Server: " + super.toString();
  }

  private static File getjavaLocation() {
    return new File("/usr/bin/java");
  }
}
