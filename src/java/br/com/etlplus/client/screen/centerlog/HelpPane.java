/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.HTMLPane;

public class HelpPane extends HTMLPane {
    public HelpPane() {
        setContentsURL("data/miniapp/demoApp_helpText.html");
        setOverflow(Overflow.AUTO);
        setStyleName("defaultBorder");
        setPadding(10);
    }
}

