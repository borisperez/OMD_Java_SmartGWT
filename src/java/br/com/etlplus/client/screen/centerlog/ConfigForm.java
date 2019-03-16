/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.TimeDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;


public class ConfigForm extends DynamicForm {

    public static ComboBoxItem itemName;
    private ButtonItem clearButtonItem;
    public DateItem dateIni;
    public TimeItem timeAddBefore, timeAddAfter;

    public ConfigForm() {

        setTop(10);
        setCellPadding(6);
        setNumCols(2);
        setStyleName("defaultBorder");
        setAutoFocus(true);
        //  setIsGroup(true);
        //  setGroupTitle("Setting to consult");

        clearButtonItem = new ButtonItem("Clear");
        clearButtonItem.setWidth(70);
        clearButtonItem.setEndRow(false);

        dateIni = new DateItem();
        dateIni.setTitle("Date");
        dateIni.setUseTextField(true);
        dateIni.setSelectOnFocus(true);
        dateIni.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);

        timeAddBefore = new TimeItem("timeMinus", "from Time");
        timeAddBefore.setTimeFormatter(TimeDisplayFormat.TOPADDED24HOURTIME);
        timeAddBefore.setEmptyDisplayValue("00");
        timeAddBefore.setUseTextField(false);
        timeAddAfter = new TimeItem("timeSum", "To Time");
        timeAddAfter.setTimeFormatter(TimeDisplayFormat.TOPADDED24HOURTIME);
        timeAddAfter.setUseTextField(false);

        setItems(dateIni, timeAddBefore, timeAddAfter, clearButtonItem);
    }

    @Override
    public HandlerRegistration addClickHandler(com.smartgwt.client.widgets.events.ClickHandler handler) {
        return clearButtonItem.addClickHandler((ClickHandler) handler);
    }

    public void addFindListener(ClickHandler handler) {
        clearButtonItem.addClickHandler(handler);
    }
}
