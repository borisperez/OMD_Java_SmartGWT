package br.com.etlplus.client.screen.centerlog;

import com.smartgwt.client.types.ExpansionMode;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.*;

public class LogListGrid {

    public ListGrid listGrid = new ListGrid() {
        @Override
        protected Canvas getExpansionComponent(ListGridRecord record) {
            Canvas canvas = super.getExpansionComponent(record);
            canvas.setMargin(5);
            return canvas;
        }
    };

    public LogListGrid(String type) {

        ListGridField logId = new ListGridField("LogId");
        logId.setCanEdit(false);
        logId.setWidth(35);
        ListGridField tools = new ListGridField("Tools");
        tools.setCanEdit(false);
        tools.setWidth(55);
        ListGridField process = new ListGridField("Process", "Process");
        process.setShowHover(true);
        process.setWidth(55);
        ListGridField called = new ListGridField("Called");
        called.setHidden(true);
        
        ListGridField analyst = new ListGridField("Analyst");
        analyst.setHidden(false);
        
        ListGridField environmentName = new ListGridField("EnvironmentName");
        environmentName.setHidden(true);
        ListGridField environmentType = new ListGridField("EnvironmentType");
        environmentType.setHidden(true);
        ListGridField envId = new ListGridField("EnvId");
        envId.setHidden(true);
        ListGridField msgCode = new ListGridField("MsgCode");
        msgCode.setCanEdit(false);
        msgCode.setWidth(70);
        ListGridField dateLogCode = new ListGridField("DateLog");
        dateLogCode.setCanEdit(false);
        dateLogCode.setWidth(110);

        ListGridField msg = new ListGridField("Msg");
        msg.setCanEdit(false);
        TextAreaItem textAreaItemMsg = new TextAreaItem();
        textAreaItemMsg.setHeight(70);
        msg.setEditorType(textAreaItemMsg);

        ListGridField dateLog = new ListGridField("Date");
        dateLog.setCanEdit(false);
        dateLog.setWidth(70);
        ListGridField timeLog = new ListGridField("Time");
        timeLog.setCanEdit(false);
        timeLog.setWidth(70);
        ListGridField status = new ListGridField("Status");
        status.setCanEdit(true);
        status.setWidth(70);
        ListGridField whoIs = new ListGridField("WhoIs");
        whoIs.setCanEdit(true);
        whoIs.setWidth(70);
        ListGridField resolution = new ListGridField("Resolution_Obs");
        resolution.setCanEdit(false);
        TextAreaItem textAreaItemResolution = new TextAreaItem();
        textAreaItemResolution.setHeight(70);
        resolution.setEditorType(textAreaItemResolution);


        //    listGrid.setPrompt("Double click or right click <BR>to show/hide Detais");
        listGrid.setHoverWidth(200);
        listGrid.setHoverHeight(20);
        listGrid.setHoverOpacity(85);
        listGrid.setCanEdit(false);
        listGrid.setCanDragRecordsOut(false);
        listGrid.setSelectionType(SelectionStyle.SINGLE);
        listGrid.setWidth100();
        listGrid.setHeight100();
        listGrid.setCanExpandRecords(true);
        listGrid.setExpansionMode(ExpansionMode.DETAIL_FIELD);
        listGrid.setDetailField("Msg");

        if ("LogScreen".equals(type)) {
            listGrid.setFields(logId, tools, dateLogCode, process, called, environmentName, environmentType, msgCode, envId, msg);
        } else if ("LogKBScreen".equals(type)){
            listGrid.setFields(logId, tools, analyst, dateLog, timeLog, process, status, called, whoIs, environmentName, environmentType, msgCode, envId, msg, resolution);
        }
    }
}
