package org.xyz.uibinder.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialog extends PopupPanel {
    private static MyBinder binder = GWT.create(MyBinder.class);

    @UiField Button btnLogin;
    @UiField TextBox txtEmail;
    @UiField(provided = true) TextBox txtPassword;
    @UiField SpanElement eEmailErrorText;
    @UiField SpanElement ePassErrorText;
    @UiField Element eEmailError;
    @UiField Element ePassError;
    @UiField MyStyle myStyle;

    public LoginDialog() {

        txtPassword = new PasswordTextBox();
        add(binder.createAndBindUi(this));

        // we want the login form to appear on the center
        // of the page when it is displayed, and want it to
        // adjust if the browser is resized.
        centerPopupOnPage();
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                centerPopupOnPage();
            }
        });
    }

    @UiFactory
    Button createLoginButton() {
        Button button = new Button();
        button.setTitle("Submit the login form");
        return button;
    }

    private void setBorderStyle(TextBox textBox, String styleName) {
        textBox.removeStyleName(myStyle.borderOk());
        textBox.removeStyleName(myStyle.borderError());
        textBox.removeStyleName(myStyle.borderEmpty());
        textBox.addStyleName(styleName);
    }

    private boolean validateEmail() {
        return txtEmail.getText().matches("[^\\s@]+@[^\\s@]+");
    }
  
  
  /* *************  UTILITY FUNCTIONS *************** */
  /*
   * These methods are used by other methods within the
   * class.  Most of these are called by event handlers.
   * 
   */

    private boolean validatePassword() {
        return txtPassword.getText().length() >= 6;
    }

    @UiHandler("txtEmail")
    void emailHasFocus(FocusEvent event) {
        eEmailError.addClassName(myStyle.hidden());
        setBorderStyle(txtEmail, myStyle.borderOk());
    }

    @UiHandler("txtEmail")
    void emailBlur(BlurEvent event) {
        if (validateEmail()) {
            eEmailError.addClassName(myStyle.hidden());
            setBorderStyle(txtEmail, myStyle.borderOk());
        } else {
            eEmailErrorText.setInnerHTML("Email is not valid");
            eEmailError.removeClassName(myStyle.hidden());
            setBorderStyle(txtEmail, myStyle.borderError());
        }
    }

  
  
  /* *************  EVENT HANDLERS *************** */
  /*
   * These are the handlers for events within the
   * widget.  Things like focus/blur events and button
   * clicks.
   * 
   */

    @UiHandler("txtPassword")
    void passwordHasFocus(FocusEvent event) {
        ePassError.addClassName(myStyle.hidden());
        setBorderStyle(txtPassword, myStyle.borderOk());
    }

    @UiHandler("txtPassword")
    void passwordBlur(BlurEvent event) {
        if (validatePassword()) {
            ePassError.addClassName(myStyle.hidden());
            setBorderStyle(txtPassword, myStyle.borderOk());
        } else {
            ePassErrorText.setInnerHTML("Password is not valid");
            ePassError.removeClassName(myStyle.hidden());
            setBorderStyle(txtPassword, myStyle.borderError());
        }
    }

    @UiHandler("btnLogin")
    void submitLoginForm(ClickEvent event) {
        if (validateEmail() && validatePassword()) {
            Window.alert("Logging in...");
        }
    }

    /**
     * A position callback to position the dialog box in the center
     * of the screen, but a little high. If the browser area is too
     * small, we use a min left/top offset of 10px.
     */
    private void centerPopupOnPage() {

        int minOffset = 10; //px
        int knownDialogWidth = 400; // this is in the CSS
        int heightAboveCenter = 200; // will set the top to 200px above center

        int left = Math.max(minOffset, (Window.getClientWidth() - knownDialogWidth) / 2);
        int top = Math.max(minOffset, (Window.getClientHeight() / 2 - heightAboveCenter));
        setPopupPosition(left, top);
        getElement().getStyle().setPosition(Style.Position.RELATIVE);
    }

    interface MyStyle extends CssResource {
        String hidden();
        String borderEmpty();
        String borderOk();
        String borderError();
    }


  
  /* *************  WIDGET CENTERING CODE *************** */
  /*
   * These routines are used to keep the widget centered on
   * the web page, even if the user resizes the browser window.
   * This isn't covered in the book in the UIBuilder chapter
   * because it is out of scope. We included it just so that
   * the widget would look nice if you ran the code :)
   *
   */

    interface MyBinder extends UiBinder<Widget, LoginDialog> {
    }

}
