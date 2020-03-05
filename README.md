# Checker Framework Support for Eclipse
An LSP4E Eclipse plugin connecting to the Checker Framework LSP language server.

## First-Time Setup Instructions
To run this plugin, clone or download from `https://github.com/eisopux/checker-framework-eclipse.git`, open the project in Eclipse, then run as Eclipse Application. Another Eclipse window will open up. 

Eclipse Plugin Development IDE (PDE) needs to be installed to run the project. Click `Help - Install New Software - Install` then search for PDE. Also install any needed dependencies from `Help - Install New Software`. Eclipse needs a restart after any installation or update.

In addition to installing the dependencies, when loading the project for the first time, also check the project `build path`. Right click the project in `Package Explorer`, select `Build Path - Configure Build Path`. Add or re-add any missing/unbound libraries.

The plugin's default preference settings are empty strings and must be specified by the user before the language server can run. When the plugin first starts, you may see an Exception and/or error message that the checker path is invalid. To fix this, go to `Preferences` in the opened-up plugin project, click `LSP Checker Framework`, and set the checker path (folder containing checker framework and language server) and type checker to use. Then, restart Eclipse to allow the plugin to see the changes.

**Note: any changes to the preference text fields require a restart to allow the plugin to see the changes**

**Note 2: make sure the folder specified in the checker path only contains one instance of language server and Checker Framework. There is currently no way of specifying which version to use in the plugin if there are multiple versions of the files.**

## Using this Plugin

You can test the plugin by clicking the Run button in the Eclipse Plugin Development IDE (PDE).

The plugin will automatically start the language server when a `.java` file is opened. To re-run the checker, either save the changes in the file or re-open the file.

If the checker path specified is missing the Checker Framework or language server, the plugin will automatically try and download the newest versions of the Checker Framework and language server into the specified path and run the language server.

Currently, only one type checker can be used at a time.

To change which checker to use, edit the checker type in the Preferences Page and change the text field. Please restart after making the change to have it take effect.

**Note: the red underlining from errors detected by the language server may underline the wrong word if tabs exist in the erroneous line due to Eclipse interpreting tabs differently than the language server**

## Exporting the Plugin

To export the plugin in order to deploy it, right click on the project in the Project Explorer Panel and click `Export`. Then, click into `Plugin Development` and select `Deployable Plug-ins and Fragments`. Click `Next`, then select the destination folder to generate the JAR file for the plugin.

## Installing the Plugin

To install the plugin, select the JAR file of the plugin and drop it into the `dropins/eclipse/plugins` directory under your target Eclipse directory. Then, run that Eclipse instance and the plugin should be up and running.
