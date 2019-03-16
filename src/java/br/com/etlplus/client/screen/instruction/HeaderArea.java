/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.instruction;

import br.com.etlplus.client.objects.User;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripSeparator;

public class HeaderArea extends VLayout {

    public HeaderArea(User user) {

        super();

        this.setHeight("40px");
        this.setWidth100();

        
        //     Inicio ToolStrip   
        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth("100%");
        Img img = new Img("omd.png");
        toolStrip.addMember(img);
        Label lblNewLabel = new Label("Welcome");
        lblNewLabel.setWidth("50px");
        lblNewLabel.setWrap(true);
        lblNewLabel.setAlign(Alignment.CENTER);
        toolStrip.addMember(lblNewLabel);
        Label lblUser;
        lblUser = new Label(user.getUserName());
        lblUser.setWidth("12px");
        toolStrip.addMember(lblUser);
        ToolStripSeparator toolStripSeparator = new ToolStripSeparator();
        toolStrip.addMember(toolStripSeparator);
        toolStrip.setShadowOffset(30);
        addMember(toolStrip);
//      Fim ToolStrip 


    }
}
