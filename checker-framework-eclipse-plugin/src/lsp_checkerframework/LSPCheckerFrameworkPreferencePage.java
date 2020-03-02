package lsp_checkerframework;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

/**
 * This class implements a sample preference page that is added to the preference dialog based on
 * the registration.
 */
public class LSPCheckerFrameworkPreferencePage extends PreferencePage
    implements IWorkbenchPreferencePage, SelectionListener, ModifyListener {

  private Text textFieldTypeChecker;
  private Text textFieldCheckerPath;
  private Text textFieldCommandLineOptions;

  /**
   * Creates composite control and sets the default layout data.
   *
   * @param parent the parent of the new composite
   * @param numColumns the number of columns for the new composite
   * @return the newly-created coposite
   */
  private Composite createComposite(Composite parent, int numColumns) {
    Composite composite = new Composite(parent, SWT.NULL);

    // GridLayout
    GridLayout layout = new GridLayout();
    layout.numColumns = numColumns;
    composite.setLayout(layout);

    // GridData
    GridData data = new GridData();
    data.verticalAlignment = GridData.FILL;
    data.horizontalAlignment = GridData.FILL;
    composite.setLayoutData(data);
    return composite;
  }

  /** (non-Javadoc) Method declared on PreferencePage */
  protected Control createContents(Composite parent) {
    PlatformUI.getWorkbench()
        .getHelpSystem()
        .setHelp(parent, LSPCheckerFrameworkConstants.PREFERENCE_PAGE_CONTEXT);

    // composite_textField << parent
    Composite composite_textField_typeChecker = createComposite(parent, 2);
    createLabel(
        composite_textField_typeChecker,
        MessageUtil.getString("Text_Field_Type_Checker")); // $NON-NLS-1$

    Composite composite_textField_checkerPath = createComposite(parent, 2);
    createLabel(
        composite_textField_checkerPath,
        MessageUtil.getString("Text_Field_Checker_Path")); // $NON-NLS-1$

    Composite composite_textField_commandOptions = createComposite(parent, 2);
    createLabel(
        composite_textField_commandOptions,
        MessageUtil.getString("Text_Field_Command_Options")); // $NON-NLS-1$

    textFieldTypeChecker = createTextField(composite_textField_typeChecker);
    textFieldCheckerPath = createTextField(composite_textField_checkerPath);
    textFieldCommandLineOptions = createTextField(composite_textField_commandOptions);

    initializeValues();

    // font = null;
    return new Composite(parent, SWT.NULL);
  }

  /**
   * Utility method that creates a label instance and sets the default layout data.
   *
   * @param parent the parent for the new label
   * @param text the text for the new label
   * @return the new label
   */
  private Label createLabel(Composite parent, String text) {
    Label label = new Label(parent, SWT.LEFT);
    label.setText(text);
    GridData data = new GridData();
    data.horizontalSpan = 2;
    data.horizontalAlignment = GridData.FILL;
    label.setLayoutData(data);
    return label;
  }

  /**
   * Create a text field specific for this application
   *
   * @param parent the parent of the new text field
   * @return the new text field
   */
  private Text createTextField(Composite parent) {
    Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
    text.addModifyListener(this);
    GridData data = new GridData();
    data.horizontalAlignment = GridData.FILL;
    data.grabExcessHorizontalSpace = true;
    data.verticalAlignment = GridData.CENTER;
    data.grabExcessVerticalSpace = false;
    text.setLayoutData(data);
    return text;
  }

  /**
   * The <code>ReadmePreferencePage</code> implementation of this <code>PreferencePage</code> method
   * returns preference store that belongs to the our plugin. This is important because we want to
   * store our preferences separately from the workbench.
   */
  protected IPreferenceStore doGetPreferenceStore() {
    return LSPCheckerFrameworkPlugin.getDefault().getPreferenceStore();
  }

  /* (non-Javadoc)
   * Method declared on IWorkbenchPreferencePage
   */
  public void init(IWorkbench workbench) {
    // do nothing
  }

  /** Initializes states of the controls using default values in the preference store. */
  private void initializeDefaults() {
    IPreferenceStore store = getPreferenceStore();

    textFieldTypeChecker.setText(store.getDefaultString(LSPCheckerFrameworkConstants.TYPE_CHECKER));
    textFieldCheckerPath.setText(store.getDefaultString(LSPCheckerFrameworkConstants.CHECKER_PATH));
    textFieldCommandLineOptions.setText(
        store.getDefaultString(LSPCheckerFrameworkConstants.COMMAND_OPTIONS));
  }

  /** Initializes states of the controls from the preference store. */
  private void initializeValues() {
    IPreferenceStore store = getPreferenceStore();

    textFieldTypeChecker.setText(store.getString(LSPCheckerFrameworkConstants.TYPE_CHECKER));
    textFieldCheckerPath.setText(store.getString(LSPCheckerFrameworkConstants.CHECKER_PATH));
    textFieldCommandLineOptions.setText(
        store.getString(LSPCheckerFrameworkConstants.COMMAND_OPTIONS));
  }

  /** (non-Javadoc) Method declared on ModifyListener */
  public void modifyText(ModifyEvent event) {
    // Do nothing on a modification in this example
  }

  /* (non-Javadoc)
   * Method declared on PreferencePage
   */
  protected void performDefaults() {
    super.performDefaults();
    initializeDefaults();
  }

  /* (non-Javadoc)
   * Method declared on PreferencePage
   */
  public boolean performOk() {
    storeValues();
    LSPCheckerFrameworkPlugin.getDefault().savePluginPreferences();
    return true;
  }

  /** Stores the values of the controls back to the preference store. */
  private void storeValues() {
    IPreferenceStore store = getPreferenceStore();

    store.setValue(LSPCheckerFrameworkConstants.TYPE_CHECKER, textFieldTypeChecker.getText());
    store.setValue(LSPCheckerFrameworkConstants.CHECKER_PATH, textFieldCheckerPath.getText());
    store.setValue(
        LSPCheckerFrameworkConstants.COMMAND_OPTIONS, textFieldCommandLineOptions.getText());
  }

  /** (non-Javadoc) Method declared on SelectionListener */
  public void widgetDefaultSelected(SelectionEvent event) {
    // Handle a default selection. Do nothing in this example
  }

  /** (non-Javadoc) Method declared on SelectionListener */
  public void widgetSelected(SelectionEvent event) {
    // Do nothing on selection in this example;
  }
}
