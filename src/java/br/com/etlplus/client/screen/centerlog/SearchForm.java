/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class SearchForm extends com.smartgwt.client.widgets.form.SearchForm {
 

    public ButtonItem findItem;
    public TextItem textoItem;

    public SearchForm() {
        setTop(10);
        setCellPadding(6);
        setNumCols(15);
        setStyleName("defaultBorder");
        findItem = new ButtonItem("Find");
        findItem.setIcon("find.png");
        findItem.setWidth(70);
        findItem.setEndRow(false);
        textoItem = new TextItem("Msg");
        textoItem.setWidth(300);
        setItems(findItem,textoItem);
    }

    public void addFindListener(ClickHandler handler) {
        findItem.addClickHandler(handler);
    }
    
    
}
