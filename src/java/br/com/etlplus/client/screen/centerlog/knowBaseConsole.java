/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import br.com.etlplus.client.objects.LogKb;
import br.com.etlplus.client.services.LogService;
import br.com.etlplus.client.services.LogServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import java.util.ArrayList;

public class knowBaseConsole extends VLayout {

    private SearchKnowBase kbSearchForm;
    private static LogListGrid kbLogListGridItem;
    SectionStackSection kbFindSection;
    SectionStackSection kbLogsItemsSection;
    SectionStackSection kbItemsDetailSection;
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again. Class KnowBaseConsole";
    final LogServiceAsync logService = GWT.create(LogService.class);
    private ItemDetailTabPane kbItemsDetailTabPane;
    private Menu kbItemListMenu;

    public knowBaseConsole() {

        SectionStack rightSideLayout = new SectionStack();
        rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
        rightSideLayout.setAnimateSections(true);

        kbFindSection = new SectionStackSection("Find Log Message");

        ImgButton hideAllButton = new ImgButton();
        hideAllButton.setSrc("arrow_in.png");
        hideAllButton.setSize(16);
        hideAllButton.setActionType(SelectionType.RADIO);
        hideAllButton.setShowFocused(false);
        hideAllButton.setShowRollOver(false);
        hideAllButton.setRadioGroup("showAll");
        hideAllButton.setShowDown(false);
        hideAllButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showAllSection();
            }
        });

        ImgButton showAllButton = new ImgButton();
        showAllButton.setSrc("arrow_out.png");
        showAllButton.setSize(16);
        showAllButton.setActionType(SelectionType.RADIO);
        showAllButton.setShowFocused(false);
        showAllButton.setShowRollOver(false);
        showAllButton.setRadioGroup("showAll");
        showAllButton.setShowDown(false);
        showAllButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hideAllSection();
            }
        });

        ImgButton hideDetailButton = new ImgButton();
        hideDetailButton.setSrc("application.png");
        hideDetailButton.setSize(16);
        hideDetailButton.setActionType(SelectionType.RADIO);
        hideDetailButton.setShowFocused(false);
        hideDetailButton.setShowRollOver(false);
        hideDetailButton.setRadioGroup("showDetail");
        hideDetailButton.setShowDown(false);
        hideDetailButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hideDetailSection();
            }
        });

        ImgButton showDetailButton = new ImgButton();
        showDetailButton.setSrc("application_split.png");
        showDetailButton.setSize(16);
        showDetailButton.setActionType(SelectionType.RADIO);
        showDetailButton.setShowFocused(false);
        showDetailButton.setShowRollOver(false);
        showDetailButton.setRadioGroup("showDetail");
        showDetailButton.setShowDown(false);
        showDetailButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showDetailSection();
            }
        });

        kbLogListGridItem = new LogListGrid("LogKBScreen");
        kbLogsItemsSection = new SectionStackSection("Log knowledge Base");
        kbLogsItemsSection.setItems(kbLogListGridItem.listGrid);
        kbLogsItemsSection.setControls(showAllButton, hideAllButton, showDetailButton, hideDetailButton);
        kbLogsItemsSection.setExpanded(true);
        kbItemsDetailSection = new SectionStackSection("Log knowledge Base Detail");
        kbItemsDetailTabPane = new ItemDetailTabPane(kbLogListGridItem, "LogKBScreen");
        kbItemsDetailSection.setItems(kbItemsDetailTabPane);
        kbItemsDetailTabPane.editorForm.getItem("saveItem").addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                sendLog(kbItemsDetailTabPane.getLogKb());
            }
        });

        kbLogListGridItem.listGrid.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
            @Override
            public void onRecordDoubleClick(RecordDoubleClickEvent event) {
                //      showDetailSection();
            }
        });
        kbLogListGridItem.listGrid.addRecordClickHandler(new RecordClickHandler() {
            @Override
            public void onRecordClick(RecordClickEvent event) {
                kbItemsDetailTabPane.updateDetails();

            }
        });

        kbLogListGridItem.listGrid.addCellSavedHandler(new CellSavedHandler() {
            @Override
            public void onCellSaved(CellSavedEvent event) {
                kbItemsDetailTabPane.updateDetails();
            }
        });

//        logListGridItem.listGrid.addCellContextClickHandler(new CellContextClickHandler() {
//            @Override
//            public void onCellContextClick(CellContextClickEvent event) {
//                itemListMenu.showContextMenu();
//                event.cancel();
//            }
//        });
        setupContextMenu();
        rightSideLayout.setSections(kbFindSection, kbLogsItemsSection, kbItemsDetailSection);
        addMember(rightSideLayout);
        loadAllComboBox();
    }

    public void setDetailTabPane(DataSource logDS) {
        kbItemsDetailTabPane.itemViewer.setDataSource(logDS);
        kbItemsDetailTabPane.editorForm.setDataSource(logDS, kbItemsDetailTabPane.editorForm.getField("LogId"),
                kbItemsDetailTabPane.editorForm.getField("Tools"),
                kbItemsDetailTabPane.editorForm.getField("Time"),
                kbItemsDetailTabPane.editorForm.getField("Analyst"),
                kbItemsDetailTabPane.editorForm.getField("Called"),
                kbItemsDetailTabPane.editorForm.getField("Date"),
                kbItemsDetailTabPane.editorForm.getField("Process"),
                kbItemsDetailTabPane.editorForm.getField("EnvId"),
                kbItemsDetailTabPane.editorForm.getField("Msg"),
                kbItemsDetailTabPane.editorForm.getField("MsgCode"),
                kbItemsDetailTabPane.editorForm.getField("Resolution_Obs"),
                kbItemsDetailTabPane.editorForm.getField("Status"),
                kbItemsDetailTabPane.editorForm.getField("WhoIs"),
                kbItemsDetailTabPane.editorForm.getField("saveItem"));
    }

    public void setLogListGrid(DataSource logDS) {
        kbLogListGridItem.listGrid.setDataSource(logDS);
//                logListGridItem.listGrid.getField("LogId"),
//                logListGridItem.listGrid.getField("Tools"),
//                logListGridItem.listGrid.getField("EnvironmentName"),
//                logListGridItem.listGrid.getField("EnvironmentType"),
//                logListGridItem.listGrid.getField("Process"),
//                logListGridItem.listGrid.getField("Analyst"),
//                logListGridItem.listGrid.getField("Called"),
//                logListGridItem.listGrid.getField("MsgCode"),
//                logListGridItem.listGrid.getField("Date"),
//                logListGridItem.listGrid.getField("Time"),
//                logListGridItem.listGrid.getField("Status"),
//                logListGridItem.listGrid.getField("WhoIs"),
//                logListGridItem.listGrid.getField("EnvId"),
//                logListGridItem.listGrid.getField("Msg"),
//                logListGridItem.listGrid.getField("Resolution_Obs"));
        kbLogListGridItem.listGrid.fetchData();
    }

    private void loadAllComboBox() {
        logService.getDistinctCbValues(new AsyncCallback<ArrayList<LogKb>>() {
            @Override
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                com.google.gwt.user.client.Window.alert("Remote Procedure Call - Failure" + "\n"
                        + SERVER_ERROR + caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<LogKb> result) {
                if (result != null) {
                    setSearchKBForm(result);
                } else {
                    com.google.gwt.user.client.Window.alert("Return is empty!");
                }
            }
        });
    }

    private void sendLog(LogKb logKb) {
        logService.updateLogKb(logKb, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                com.google.gwt.user.client.Window.alert("Remote Procedure Call - Failure" + "\n"
                        + SERVER_ERROR + caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    com.google.gwt.user.client.Window.alert("Return is: " + result);
                } else {
                    com.google.gwt.user.client.Window.alert("Return is empty!");
                }
            }
        });
    }
    
    public void setSearchKBForm(ArrayList<LogKb> result) {
        LoadCbKbRecord.setTypeRecords(result);
        // searchKBForm = new SearchKnowBase(setMsgCodeDS(LoadCbKbRecord.getRecordsMsgCode()), setToolsDS(LoadCbKbRecord.getRecordsTools()));

        kbSearchForm = new SearchKnowBase(setDS(LoadCbKbRecord.getRecordsMsgCode(), new String[]{"Tools", "MsgCode"}),
                setDS(LoadCbKbRecord.getRecordsTools(), new String[]{"Tools"}),
                setDS(LoadCbKbRecord.getRecordsAnalyst(), new String[]{"Analyst"}));
        kbSearchForm.setHeight(30);
        kbSearchForm.addFindListener(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                findKBLogs();
            }
        });
        kbFindSection.setItems(kbSearchForm);
        kbFindSection.setExpanded(true);
    }

    public void findKBLogs() {
        LogKb logkb = getParamFromSearch();
        logService.getLogsKb(logkb, new AsyncCallback<ArrayList<LogKb>>() {
            @Override
            public void onFailure(Throwable caught) {
                com.google.gwt.user.client.Window.alert("Remote Procedure Call - Failure" + "\n"
                        + SERVER_ERROR + caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<LogKb> result) {
                if (result != null) {
                    Object[] arrayObj = new Object[result.size()];
                    result.toArray(arrayObj);
                    LogListGridRecord.setNewRecords(arrayObj);
                    DataSource logDSKb = new LogDSKb("LogKBScreen");
                    logDSKb.setTestData(LogListGridRecord.getRecordsKb());
                    setLogListGrid(logDSKb);
                    setDetailTabPane(logDSKb);
                    kbFindSection.setExpanded(false);
                } else {
                    com.google.gwt.user.client.Window.alert("Return is empty!");
                }
            }
        });
    }

    public static DataSource setDS(ListGridRecord[] list, String[] campo) {
        DataSource ds = new DataSource();
        DataSourceTextField[] fields = new DataSourceTextField[campo.length];
        for (int i = 0; i < campo.length; i++) {
            DataSourceTextField field = new DataSourceTextField(campo[i], campo[i]);
            fields[i] = field;
        }
        ds.setFields(fields);
        ds.setClientOnly(true);
        ds.setTestData(list);
        ds.fetchData();
        return ds;

    }

    public LogKb getParamFromSearch() {
        LogKb logkb = new LogKb();
        if ("Msg".equals(kbSearchForm.radioGroupItem.getDisplayValue())) {
            logkb.setMsg(kbSearchForm.getValueAsString("Msg"));
        }
        if ("MsgCode".equals(kbSearchForm.radioGroupItem.getDisplayValue())) {
            logkb.setTools(kbSearchForm.getValueAsString("Tools"));
            logkb.setMsgCode(kbSearchForm.getValueAsString("MsgCode"));
        }
        if ("Analyst".equals(kbSearchForm.radioGroupItem.getDisplayValue())) {
            logkb.setAnalyst(kbSearchForm.getValueAsString("Analyst"));
        }
        if ("Tools".equals(kbSearchForm.radioGroupItem.getDisplayValue())) {
            logkb.setTools(kbSearchForm.getValueAsString("Tools"));
        }
        return logkb;
    }

    private void setupContextMenu() {
        kbItemListMenu = new Menu();
        kbItemListMenu.setCellHeight(22);

        MenuItem kbDetailsMenuItem = new MenuItem("Hide Details", "application_form.png");
        kbDetailsMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                hideDetailSection();
            }
        });

        final MenuItem kbEditMenuItem = new MenuItem("Show Details", "icon_edit.png");
        kbEditMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                showDetailSection();
            }
        });

//        final MenuItem deleteMenuItem = new MenuItem("Delete Item", "delete.png");
//        deleteMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
//            @Override
//            public void onClick(MenuItemClickEvent event) {
//                logListGridItem.listGrid.removeSelectedData();
//                itemDetailTabPane.clearDetails(null);
//            }
//        });

        kbItemListMenu.setData(kbDetailsMenuItem, kbEditMenuItem);
    }

    private void showDetailSection() {
        kbItemsDetailSection.setHidden(false);
        kbItemsDetailSection.setExpanded(true);
        kbLogListGridItem.listGrid.setHeight(250);
    }

    private void showAllSection() {
        kbFindSection.setHidden(false);
    }

    private void hideDetailSection() {
        kbItemsDetailSection.setExpanded(false);
        kbItemsDetailSection.setHidden(true);
        kbLogListGridItem.listGrid.setHeight100();
    }

    private void hideAllSection() {
        kbFindSection.setHidden(true);
    }
}
