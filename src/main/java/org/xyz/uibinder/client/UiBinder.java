package org.xyz.uibinder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import org.xyz.uibinder.client.widgets.LoginDialog;

public class UiBinder implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new LoginDialog());
    }
}
