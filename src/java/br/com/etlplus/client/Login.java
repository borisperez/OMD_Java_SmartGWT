/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client;

import br.com.etlplus.client.objects.User;
import br.com.etlplus.client.screen.main.ScreenMain;
import br.com.etlplus.client.services.LoginService;
import br.com.etlplus.client.services.LoginServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.*;
import com.google.gwt.core.client.GWT;
//import com.nkdata.gwt.streamer.client.Streamer;

import com.smartgwt.client.widgets.form.fields.PasswordItem;

public class Login extends HLayout implements EntryPoint {

    private DynamicForm formlogin = new DynamicForm();
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";
    private TextItem loginItem = new TextItem("login", "login");
    private PasswordItem passwordItem = new PasswordItem();
    final LoginServiceAsync getLoginService = GWT.create(LoginService.class);
    private String login, password;

    @Override
    public void onModuleLoad() {
        setWidth100();
        setHeight100();
        setLayoutMargin(5);
        login();

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void login() {
        final Window winModal = new Window();
        winModal.setWidth(360);
        winModal.setHeight(250);
        winModal.setFooterHeight(20);
        winModal.setTitle("OMD Login");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.centerInPage();
        winModal.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                winModal.destroy();
            }
        });

        Img omdLogin = new Img("omd.png");
        formlogin.setHeight100();
        formlogin.setWidth100();
        formlogin.setPadding(5);
        formlogin.setLayoutAlign(VerticalAlignment.CENTER);
        passwordItem.setName("password");

        ButtonItem okButton = new ButtonItem("Acesso");
        okButton.setAlign(Alignment.RIGHT);
        okButton.setWidth(60);
        okButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                if (formlogin.validate()) {
                    setLogin(loginItem.getValue().toString());
                    setPassword(passwordItem.getValue().toString());
                    sendLogin();
                    winModal.destroy();
                }

            }
        });

        formlogin.setFields(loginItem, passwordItem, okButton);
        formlogin.setValue("login", "boris.perez");
        formlogin.setValue("password", "xpto0101");
        winModal.addItem(omdLogin);
        winModal.addItem(formlogin);
        winModal.show();
    }

    private void sendLogin() {
        getLoginService.authenticUser(getLogin(),getPassword(),
                new AsyncCallback<User>() {
            @Override
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                com.google.gwt.user.client.Window.alert("Remote Procedure Call - Failure" + "\n"
                        + SERVER_ERROR + caught.getMessage());
            }
            @Override
            public void onSuccess(User rsUser) {
                if (rsUser != null) {
                    ScreenMain screenmain = new ScreenMain(rsUser);
                    screenmain.show();
                } else {
                    com.google.gwt.user.client.Window.alert("Invalid User or Password!");
                    login();
                }
            }

        });
    }
}
