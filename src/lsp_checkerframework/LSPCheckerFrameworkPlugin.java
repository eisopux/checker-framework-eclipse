package lsp_checkerframework;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * This is the top-level class of the Readme plugin tool.
 *
 * @see AbstractUIPlugin for additional information on UI plugins
 */
public class LSPCheckerFrameworkPlugin extends AbstractUIPlugin {
    
    public static final String PLUGIN_ID = "org.eclipse.ui.examples.readmetool"; //$NON-NLS-1$
    
    /**
     * Default instance of the receiver
     */ 
    private static LSPCheckerFrameworkPlugin inst;

    /**
     * Creates the Readme plugin and caches its default instance
     */
    public LSPCheckerFrameworkPlugin() {
        if (inst == null)
            inst = this;
    }

    /**
     * Gets the plugin singleton.
     *
     * @return the default ReadmePlugin instance
     */
    static public LSPCheckerFrameworkPlugin getDefault() {
        return inst;
    }

}
