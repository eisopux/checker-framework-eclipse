package lsp_checkerframework;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Initializes the preferences for the readme plug-in.
 * 
 * @since 3.0
 */
public class ReadmePreferenceInitializer extends AbstractPreferenceInitializer {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        // These settings will show up when the Readme preference page
        // is shown for the first time.
        IPreferenceStore store = ReadmePlugin.getDefault().getPreferenceStore();
        store.setDefault("BOOLEAN 1", true);
        store.setDefault("BOOLEAN 2", true);
        store.setDefault("BOOLEAN 3", false);
        store.setDefault("CHOICE", 2);
        store.setDefault("TEXT", "Default_text"); //$NON-NLS-1$
    }

}