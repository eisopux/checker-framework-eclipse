package org.checkerframework.languageserver.eclipse;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * This is the top-level class of the LSPCheckerFramework plugin tool.
 *
 * @see AbstractUIPlugin for additional information on UI plugins
 */
public class LSPCheckerFrameworkPlugin extends AbstractUIPlugin {

  public static final String PLUGIN_ID = "org.eclipse.ui.examples.readmetool"; // $NON-NLS-1$

  /** Default instance of the receiver */
  private static LSPCheckerFrameworkPlugin inst;

  /** Creates the plugin and caches its default instance */
  public LSPCheckerFrameworkPlugin() {
    if (inst == null) inst = this;
  }

  /**
   * Gets the plugin singleton.
   *
   * @return the default LSPCheckerFrameworkPlugin instance
   */
  public static LSPCheckerFrameworkPlugin getDefault() {
    return inst;
  }
}
