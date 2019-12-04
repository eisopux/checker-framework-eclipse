/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package lsp_checkerframework;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Initializes the preferences for the readme plug-in.
 * 
 * @since 3.0
 */
public class LSPCheckerFrameworkPreferenceInitializer extends AbstractPreferenceInitializer {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        // These settings will show up when the preference page is shown for the first time.
    	
    	IPreferenceStore store = LSPCheckerFrameworkPlugin.getDefault().getPreferenceStore();
        store.setDefault("TYPE_CHECKER", ""); //$NON-NLS-1$ //$NON-NLS-2$
        store.setDefault("CHECKER_PATH", ""); //$NON-NLS-1$ //$NON-NLS-2$
        store.setDefault("COMMAND_OPTIONS", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
