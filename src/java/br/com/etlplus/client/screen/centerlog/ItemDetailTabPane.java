/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import br.com.etlplus.client.objects.LogKb;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.viewer.DetailViewer;

import java.util.HashMap;
import java.util.Map;

public class ItemDetailTabPane extends TabSet {

    public DetailViewer itemViewer;
    public DynamicForm editorForm;
    private Label editorLabel;
    public LogListGrid itemListGrid;
    public IPickTreeItem category;
    private String type;

    public ItemDetailTabPane(LogListGrid itemListGrid, String Type) {
        this.itemListGrid = itemListGrid;
        setType(Type);
        itemViewer = new DetailViewer();
        itemViewer.setWidth100();
        itemViewer.setMargin(15);
        itemViewer.setEmptyMessage("Select an item to view its details");

        editorLabel = new Label();
        editorLabel.setWidth100();
        editorLabel.setHeight100();
        editorLabel.setAlign(Alignment.CENTER);
        editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

        editorForm = new DynamicForm();
        editorForm.setWidth(650);
        editorForm.setMargin(15);
        editorForm.setNumCols(6);
        editorForm.setCellPadding(5);
        editorForm.setAutoFocus(false);

        TextItem logId = new TextItem("LogId", "LogId");
        logId.setCanEdit(false);
        TextItem tools = new TextItem("Tools", "Tools");
        tools.setCanEdit(false);
        TextItem process = new TextItem("Process", "Process");
        process.setCanEdit(false);
        TextItem analyst = new TextItem("Analyst", "Analyst");
        analyst.setCanEdit(true);
        TextItem called = new TextItem("Called", "Called");
        called.setCanEdit(true);
        TextItem environmentName = new TextItem("EnvironmentName", "EnvironmentName");
        environmentName.setCanEdit(false);
        TextItem environmentType = new TextItem("EnvironmentType", "EnvironmentType");
        environmentType.setCanEdit(false);
        TextItem msgCode = new TextItem("MsgCode", "MsgCode");
        msgCode.setCanEdit(false);
        TextAreaItem msg = new TextAreaItem("Msg");
        msg.setWidth(300);
        msg.setRowSpan(3);
        msg.setCanEdit(false);
        TextAreaItem resolution = new TextAreaItem("Resolution_Obs");
        resolution.setWidth(300);
        resolution.setRowSpan(3);
        resolution.setCanEdit(true);
        TextItem dateLog = new TextItem("DateLog", "DateLog");
        dateLog.setCanEdit(false);
        TextItem data = new TextItem("Date", "Date");
        data.setCanEdit(false);
        TextItem timeLog = new TextItem("Time", "Time");
        timeLog.setCanEdit(false);
        TextItem envId = new TextItem("EnvId", "EnvId");
        envId.setCanEdit(false);
        TextItem statusLog = new TextItem("Status", "Status");
        statusLog.setCanEdit(true);
        TextItem whoIsLog = new TextItem("WhoIs", "Who is Analyzing");
        whoIsLog.setCanEdit(true);


        ButtonItem saveButton = new ButtonItem("saveItem", "Save");
        saveButton.setAlign(Alignment.LEFT);
        saveButton.setWidth(100);
        saveButton.setColSpan(4);
        if ("LogScreen".equals(type)) {
            editorForm.setFields(logId, msgCode, tools, process, analyst, called, environmentName, environmentType, dateLog, statusLog, whoIsLog, msg, resolution, saveButton);
            editorForm.setColWidths(50, 100, 100, 100, 100, 100, 100, 100, 100, 300, 300, 200);
        } else if ("LogKBScreen".equals(type)) {
            editorForm.setFields(logId, msgCode, tools, timeLog, analyst, called, environmentName, environmentType, data, process, envId, statusLog, whoIsLog, msg, resolution, saveButton);
            editorForm.setColWidths(50, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 300, 300, 200);
        }
        Tab viewTab = new Tab("View");
        viewTab.setIcon("application_form.png", 16);
        viewTab.setWidth(70);
        viewTab.setPane(itemViewer);

        Tab editTab = new Tab("Edit");
        editTab.setIcon("icon_edit.png", 16);
        editTab.setWidth(70);
        editTab.setPane(editorForm);

        setTabs(viewTab, editTab);

        addTabSelectedHandler(new TabSelectedHandler() {
            public void onTabSelected(TabSelectedEvent event) {
                updateDetails();
            }
        });
    }

    public void clearDetails(Record selectedCategoryRecord) {
        int selectedTab = getSelectedTabNumber();
        if (selectedTab == 0) {
            //view tab : show empty message
            itemViewer.setData((Record[]) null);
        } else {
            // edit tab : show new record editor, or empty message
            if (selectedCategoryRecord != null) {
                updateTab(1, editorForm);
                Map initialValues = new HashMap();
                initialValues.put("category", selectedCategoryRecord.getAttribute("categoryName"));
                editorForm.editNewRecord(initialValues);
            } else {
                updateTab(1, editorLabel);
            }
        }
    }

    public void updateDetails() {
        if (itemListGrid.listGrid.anySelected()) {
            Record selectedRecord = itemListGrid.listGrid.getSelectedRecord();
            int selectedTab = getSelectedTabNumber();
            if (selectedTab == 0) {
                //view tab : show empty message
                itemViewer.setData(new Record[]{selectedRecord});
            } else {
                // edit tab : show record editor
                editorForm.editRecord(selectedRecord);
            }
        }
    }

    public LogKb getLogKb() {
        LogKb logkb = new LogKb();
        logkb.setLogId(Integer.parseInt(editorForm.getValueAsString("LogId")));
        logkb.setTools(editorForm.getValueAsString("Tools"));
        logkb.setProcess(editorForm.getValueAsString("Process"));
        logkb.setAnalyst(editorForm.getValueAsString("Analyst"));
        logkb.setCalled(editorForm.getValueAsString("Called"));
        logkb.setEnvironmentName(editorForm.getValueAsString("EnvironmentName"));
        logkb.setEnvironmentType(editorForm.getValueAsString("EnvironmentType"));
        logkb.setMsgCode(editorForm.getValueAsString("MsgCode"));
        if ("LogScreen".equals(getType())) {
            logkb.setDate(getDateFromForm(editorForm.getValueAsString("DateLog")));
            logkb.setTime(getTimeFromForm(editorForm.getValueAsString("DateLog")));
        } else if ("LogKBScreen".equals(type)) {
            logkb.setDate(editorForm.getValueAsString("Date"));
            logkb.setTime(editorForm.getValueAsString("Time"));
        }
        logkb.setMsg(editorForm.getValueAsString("Msg"));
        logkb.setStatus(editorForm.getValueAsString("Status"));
        logkb.setWhoIs(editorForm.getValueAsString("WhoIs"));
        logkb.setResolution_Obs(editorForm.getValueAsString("Resolution_Obs"));
        logkb.setEnvId(Integer.parseInt(editorForm.getValueAsString("EnvId")));
        return logkb;
    }

    private String getDateFromForm(String DateLog) {
        return DateLog.substring(0, 10);
    }

    private String getTimeFromForm(String DateLog) {
        return DateLog.substring(11);
    }

    public void clearItems() {
        editorForm.getItem("LogId").setValue("");
        editorForm.getItem("Tools").setValue("");
        editorForm.getItem("Process").setValue("");
        editorForm.getItem("Analyst").setValue("");
        editorForm.getItem("Called").setValue("");
        editorForm.getItem("EnvironmentName").setValue("");
        editorForm.getItem("EnvironmentType").setValue("");
        editorForm.getItem("MsgCode").setValue("");
        editorForm.getItem("Msg").setValue("");
        editorForm.getItem("Resolution_Obs").setValue("");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
