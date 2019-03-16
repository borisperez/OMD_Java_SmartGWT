/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.widgets.layout.HLayout;

public class OpenKbForm extends HLayout {

    public OpenKbForm() {
        setWidth100();
        setHeight(50);
        setLayoutMargin(6);
        setMembersMargin(6);
        setBorder("4px ridge gray");
        setAlign(Alignment.CENTER);
        setDefaultLayoutAlign(Alignment.CENTER);

        setTop(10);

        final IButton openKbButtonItem = new IButton("Open knowledge Base ");
        openKbButtonItem.setStyleName("Legal");
        openKbButtonItem.setWidth(140);
        openKbButtonItem.setShowRollOver(true);
        openKbButtonItem.setShowDisabled(true);
        openKbButtonItem.setShowDown(true);
        //      openKbButtonItem.setMargin(10);
        openKbButtonItem.setIcon("application_tile_vertical.png");
        openKbButtonItem.setPrompt("Open knowledge base to compare and edit");
        openKbButtonItem.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Rectangle rect = openKbButtonItem.getPageRect();
                final Canvas animateOutline = new Canvas();
                animateOutline.setBorder("2px solid black");
                animateOutline.setTop(rect.getTop());
                animateOutline.setLeft(rect.getLeft());
                animateOutline.setWidth(rect.getLeft());
                animateOutline.setHeight(rect.getHeight());
                animateOutline.show();
                animateOutline.animateRect(0, 0, Page.getWidth(), Page.getHeight(), new AnimationCallback() {
                    public void execute(boolean earlyFinish) {
                        animateOutline.hide();
                        final OpenKbConsole openKbConsole = new OpenKbConsole();
                        openKbConsole.addCloseClickHandler(new CloseClickHandler() {
                            public void onCloseClick(CloseClickEvent event) {
                                animateOutline.setRect(0, 0, Page.getWidth(), Page.getHeight());
                                animateOutline.show();
                                openKbConsole.destroy();
                                Rectangle targetRect = openKbButtonItem.getPageRect();
                                animateOutline.animateRect(targetRect.getLeft(), targetRect.getTop(), targetRect.getWidth(),
                                        targetRect.getHeight(), new AnimationCallback() {
                                    public void execute(boolean earlyFinish) {
                                        animateOutline.hide();
                                    }
                                }, 500);

                            }
                        });
                        openKbConsole.show();
                    }
                }, 500);
            }
        });

        addMembers(openKbButtonItem);
    }
//    @Override
//    public HandlerRegistration addClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
//        return openKbButtonItem.addClickHandler((ClickHandler) handler);
//    }
//
//    public void addFindListener(ClickHandler handler) {
//        openKbButtonItem.addClickHandler(handler);
//    }

    class OpenKbConsole extends Window {

        OpenKbConsole() {
        setTitle("knowledge Base");
        setWidth100();
        setHeight100();
        setLayoutMargin(10);
        setHeaderIcon("layout_content.png");
        setShowMinimizeButton(false);
        setShowCloseButton(true);
        setCanDragReposition(false);
        setCanDragResize(false);
        setShowShadow(false);
            addItem(new knowBaseConsole());
        }
    }
}
