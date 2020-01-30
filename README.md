# Checker Framework Support for Eclipse
An LSP4E Eclipse plugin connecting to the Checker Framework LSP language server.

## First-Time Install Setup Instructions

To run this plugin, clone or download from https://github.com/eisopux/checker-framework-eclipse.git, open the project in eclipse, then run as Eclipse Application. Another eclipse window will open up. Install any needed dependencies from Help-Install New Software. Eclipse would need a restart after any installation or update.

The plugin's default preference settings are empty strings and must be specified by the user before the language server can run. When the plugin first starts, you may see an Exception and/or error message that the checker path is invalid. To fix this, go to `Preferences`, click `LSP Checker Framework`, and set the checker path (folder containing checker framework and language server) and type checker to use. Then, restart Eclipse to allow the plugin to see the changes.

If the checker path specified is missing the checker framework or language server, the plugin will automatically try and download the newest versions of the checker framework and language server into the specified path and run the language server.

At the current time, only one type checker can be used at a time.

**Note: any changes to the preference text fields require a restart to allow the plugin to see the changes**

**Note 2: make sure the folder specified in the checker path only contains one instance of language server and checker framework. There is currently no way of specifying which version to use in the plugin if there are multiple versions of the files.**

## Using this Plugin

The plugin will automatically start the language server when a `.java` file is opened. To re-run the checker, either save the changes in the file or re-open the file.

If the checker path specified is missing the checker framework or language server, the plugin will automatically try and download the newest versions of the checker framework and language server into the specified path and run the language server.

To change which checker to use, edit the checker type in the Preferences Page and change the text field. Please restart after making the change to have it take effect.

**Note: the red underlining from errors detected by the language server may underline the wrong word if tabs exist in the erroneous line due to Eclipse interpreting tabs differently than the language server**
