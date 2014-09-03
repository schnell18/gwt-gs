# Introduction
This project shows the use of [gwt-gradle-plugin][1] which includes:
* GWT dev mode -- the gwtDev task
* GWT dev mode JPDA debug -- the gwtDevDebug task
* GWT super dev mode -- the gwtSuperDev task

It also demos the declarative UI creation by using UiBinder.

# Build war
You can build the sample application as a war package as usual using
this command:

    gradle build

And find the war under build/libs.

# Run dev mode
To run the dev mode, you type:

    gradle gwtDev

# Run super dev mode
To run the dev mode, you type:

    gradle gwtSuperDev

# Run dev mode with JPDA debugging
To run the dev mode with remote debugging enabled, you type:

    gradle gwtDevDebug

This launches the GWT dev mode and waiting for any JPDA compliant debugger
(IDEA, Eclipse or jdb) to connect on TCP port 5005.

[1]: https://github.com/steffenschaefer/gwt-gradle-plugin
