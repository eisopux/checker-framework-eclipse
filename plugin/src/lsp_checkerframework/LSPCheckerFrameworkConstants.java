package lsp_checkerframework;

/** This interface contains constants for use only within the LSP CheckerFramework Plugin. */
public interface LSPCheckerFrameworkConstants {
  public static final String PLUGIN_ID = "lsp_checkerframework"; // $NON-NLS-1$

  public static final String PREFIX = PLUGIN_ID + "."; // $NON-NLS-1$

  public static final String TYPE_CHECKER = PREFIX + "textTypeChecker"; // $NON-NLS-1$

  public static final String CHECKER_PATH = PREFIX + "textCheckerPath";

  public static final String COMMAND_OPTIONS = PREFIX + "textCommandOptions";

  public static final String PREFERENCE_PAGE_CONTEXT =
      PREFIX + "preference_page_context"; // $NON-NLS-1$
}
