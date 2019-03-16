/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.instruction;

import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class InstructionScreen {

    public static HLayout mainLayout = new HLayout();
    public static HLayout eastLayout = new HLayout();
    public static HLayout westLayout = new NavigationArea();
    public Tab tabInstruction;

    public InstructionScreen(Tab tab) throws IllegalStateException {
        eastLayout.setID("eastLayoutInst");
        Img logoEtlPlus = new Img("ETL_Plus_Logo.png", 100, 100);
        logoEtlPlus.setShowShadow(true);
        logoEtlPlus.setShadowSoftness(10);
        logoEtlPlus.setShadowOffset(5);
        logoEtlPlus.setImageType(ImageStyle.CENTER);
        logoEtlPlus.setPosition(Positioning.RELATIVE);
        logoEtlPlus.setBorder("1px solid gray");
        logoEtlPlus.setLeft(120);
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        westLayout.setWidth("15%");
        eastLayout.setWidth("85%");
        eastLayout.setBorder("1px solid gray");
        eastLayout.setMembersMargin(5);
        final Canvas eastCanvas = new Canvas();
        eastCanvas.setHeight100();
        eastCanvas.setPadding(2);
        eastCanvas.setOverflow(Overflow.HIDDEN);
        eastCanvas.setCanDragResize(true);
        eastCanvas.setShowEdges(true);
        eastCanvas.addChild(logoEtlPlus);
        eastLayout.addMember(eastCanvas);
        mainLayout.addMember(westLayout);
        mainLayout.addMember(eastLayout);
        tab.setPane(mainLayout);
        setTabInstruction(tab);
    }

    public Tab getTabInstruction() {
        return tabInstruction;
    }

    public void setTabInstruction(Tab tab) {
        this.tabInstruction = tab;
    }
}
