package lsp_checkerframework;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * This is the top-level class of the Readme plugin tool.
 *
 * @see AbstractUIPlugin for additional information on UI plugins
 */
public class ReadmePlugin extends AbstractUIPlugin {
    
    public static final String PLUGIN_ID = "lsp_checkerframework"; //$NON-NLS-1$
    
    /**
     * Default instance of the receiver
     */ 
    private static ReadmePlugin inst;

    /**
     * Creates the Readme plugin and caches its default instance
     */
    public ReadmePlugin() {
    	super();
        if (inst == null) {
        	System.out.println("Initializing preference store");
            inst = this;
        }
    }
    
    @Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		inst = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		inst = null;
		super.stop(context);
	}

    /**
     * Gets the plugin singleton.
     *
     * @return the default ReadmePlugin instance
     */
    static public ReadmePlugin getDefault() {
    	System.out.println("Retrieving preference store");
        return inst;
    }

}