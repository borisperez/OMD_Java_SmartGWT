package br.com.etlplus.client.screen.console;

import br.com.etlplus.client.objects.Env;
import br.com.etlplus.client.services.LogService;
import br.com.etlplus.client.services.LogServiceAsync;
import br.com.etlplus.client.objects.Log;
import br.com.etlplus.client.objects.LogKb;
import br.com.etlplus.client.screen.centerlog.ConfigForm;
import br.com.etlplus.client.screen.centerlog.EnvDS;
import br.com.etlplus.client.screen.centerlog.EnvironmentTreeGrid;
import br.com.etlplus.client.screen.centerlog.ItemDetailTabPane;
import br.com.etlplus.client.screen.centerlog.LogDS;
import br.com.etlplus.client.screen.centerlog.LogListGrid;
import br.com.etlplus.client.screen.centerlog.LogListGridRecord;
import br.com.etlplus.client.screen.centerlog.OpenKbForm;
import br.com.etlplus.client.screen.centerlog.SearchForm;
import br.com.etlplus.client.services.EnvService;
import br.com.etlplus.client.services.EnvServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;
import java.util.ArrayList;

public class LogAnalysisConsole extends HLayout {

    private EnvironmentTreeGrid environmentTree;
    private ConfigForm configForm;
    private OpenKbForm openKbForm;
    private static LogListGrid logListGridItem;
    private ItemDetailTabPane itemDetailTabPane;
    private Menu itemListMenu;
    final LogServiceAsync logService = GWT.create(LogService.class);
    final EnvServiceAsync getEnvService = GWT.create(EnvService.class);
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";
    private static Log[] logsRequest;
    private static Env[] envsRequest;
    private DataSource envDs;
    private SectionStackSection envSection;
    private SectionStackSection itemDetailsSection;
    private String serverName;
    private SearchForm searchForm;
    private DataSource logDS;
    SectionStackSection logsItemsSection;
    SectionStackSection findSection;
    SectionStack leftSideLayout;
    SectionStackSection configSection;
    SectionStackSection logCompareSection;

    public LogAnalysisConsole() {

        setWidth100();
        setHeight100();
        setLayoutMargin(10);

        configForm = new ConfigForm();
        configForm.setHeight(100);
        configForm.dateIni.setCanFocus(true);

        configForm.addFindListener(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                LogListGridRecord.lista.clear();
                logDS.destroy();
                LogListGridRecord.cleanRecords();
                setLogListGrid();
                hideDetailSection();

            }
        });

        configSection = new SectionStackSection("Setting to consult");
        configSection.setItems(configForm);
        configSection.setExpanded(true);


        setupContextMenu();

        logListGridItem = new LogListGrid("LogScreen");
        logListGridItem.listGrid.addRecordDoubleClickHandler(new RecordDoubleClickHandler() {
            @Override
            public void onRecordDoubleClick(RecordDoubleClickEvent event) {
                //      showDetailSection();
            }
        });
        logListGridItem.listGrid.addRecordClickHandler(new RecordClickHandler() {
            @Override
            public void onRecordClick(RecordClickEvent event) {
                itemDetailTabPane.updateDetails();

            }
        });

        logListGridItem.listGrid.addCellSavedHandler(new CellSavedHandler() {
            @Override
            public void onCellSaved(CellSavedEvent event) {
                itemDetailTabPane.updateDetails();
            }
        });

        logListGridItem.listGrid.addCellContextClickHandler(new CellContextClickHandler() {
            @Override
            public void onCellContextClick(CellContextClickEvent event) {
                itemListMenu.showContextMenu();
                event.cancel();
            }
        });

        leftSideLayout = new SectionStack();
        leftSideLayout.setWidth(280);
        leftSideLayout.setShowResizeBar(true);
        leftSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
        leftSideLayout.setAnimateSections(true);

        envSection = new SectionStackSection("Environments");


        openKbForm = new OpenKbForm();
        openKbForm.setHeight(100);

        logCompareSection = new SectionStackSection("Log Saved Compare");
        logCompareSection.setResizeable(true);
        logCompareSection.setExpanded(false);
        logCompareSection.setItems(openKbForm);

        leftSideLayout.setSections(configSection, envSection, logCompareSection);

        SectionStack rightSideLayout = new SectionStack();
        rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
        rightSideLayout.setAnimateSections(true);

        searchForm = new SearchForm();
        searchForm.setHeight(30);
        searchForm.addFindListener(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                findItems();
            }
        });
        findSection = new SectionStackSection("Find Log Message");
        findSection.setItems(searchForm);
        findSection.setExpanded(true);

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
        logsItemsSection = new SectionStackSection("Log Message");
        logsItemsSection.setItems(logListGridItem.listGrid);
        logsItemsSection.setExpanded(true);
        logsItemsSection.setControls(showAllButton, hideAllButton, showDetailButton, hideDetailButton);

        //     itemDetailTabPane = new ItemDetailTabPane(envDs, logListGridItem);
        itemDetailsSection = new SectionStackSection("Details");
        hideDetailSection();
//        itemDetailsSection.setItems(itemDetailTabPane);

        rightSideLayout.setSections(findSection, logsItemsSection, itemDetailsSection);

        addMember(leftSideLayout);
        addMember(rightSideLayout);
        sendEnv();
        this.show();
        //    SC.say("Configure", "Before All, please, configure the date/time range to read logs message");


    }

    private void setDataSourceEnvDS(DataSource envDS) {
        this.envDs = envDS;
    }

    private void setupContextMenu() {
        itemListMenu = new Menu();
        itemListMenu.setCellHeight(22);

        MenuItem detailsMenuItem = new MenuItem("Hide Details", "application_form.png");
        detailsMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                hideDetailSection();
            }
        });

        final MenuItem editMenuItem = new MenuItem("Show Details", "icon_edit.png");
        editMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
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

        itemListMenu.setData(detailsMenuItem, editMenuItem);
    }

    public void findItems() {
        Criteria findValues = null;
        if (searchForm.textoItem.getValueAsString() != "") {
            findValues = searchForm.getValuesAsCriteria();
        }
        logListGridItem.listGrid.filterData(findValues);
        //   itemDetailTabPane.clearDetails(environmentTree.getSelectedRecord());
    }

    private void sendLog(String serverName, int maxLog, String date, String dateAddBefore, String dateAddAfter) {
        logService.getLogs(serverName, maxLog, date, dateAddBefore, dateAddAfter, new AsyncCallback<ArrayList<Log>>() {
            @Override
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                com.google.gwt.user.client.Window.alert("Remote Procedure Call - Failure" + "\n"
                        + SERVER_ERROR + caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<Log> result) {
                if (result != null) {
                    Object[] arrayObj = new Object[result.size()];
                    result.toArray(arrayObj);
                    LogListGridRecord.setNewRecords(arrayObj);
                    setLogListGrid();
                    setDetailTabPane();
                    searchForm.setDataSource(logDS, searchForm.findItem, searchForm.textoItem);
                } else {
                    com.google.gwt.user.client.Window.alert("Return is empty!");
                }
            }
        });
    }

    private void sendLog(LogKb logKb) {
        logService.setLog(logKb, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                com.google.gwt.user.client.Window.alert("Remote Procedure Call - Failure" + "\n"
                        + SERVER_ERROR + caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    com.google.gwt.user.client.Window.alert("Return is " + result);
                } else {
                    com.google.gwt.user.client.Window.alert("Return is empty!");
                }
            }
        });
    }

    public void setDetailTabPane() {
        itemDetailTabPane.itemViewer.setDataSource(logDS);
        itemDetailTabPane.editorForm.setDataSource(logDS,
                itemDetailTabPane.editorForm.getField("LogId"),
                itemDetailTabPane.editorForm.getField("Tools"),
                itemDetailTabPane.editorForm.getField("Process"),
                itemDetailTabPane.editorForm.getField("Analyst"),
                itemDetailTabPane.editorForm.getField("Called"),
                itemDetailTabPane.editorForm.getField("DateLog"),
                itemDetailTabPane.editorForm.getField("Msg"),
                itemDetailTabPane.editorForm.getField("MsgCode"),
                itemDetailTabPane.editorForm.getField("Resolution_Obs"),
                itemDetailTabPane.editorForm.getField("Status"),
                itemDetailTabPane.editorForm.getField("WhoIs"),
                itemDetailTabPane.editorForm.getField("saveItem"));
    }

    public void setLogListGrid() {
        logDS = new LogDS("LogScreen");
        logDS.setTestData(LogListGridRecord.getRecords());
        logListGridItem.listGrid.setDataSource(logDS, logListGridItem.listGrid.getField("LogId"),
                logListGridItem.listGrid.getField("Tools"),
                logListGridItem.listGrid.getField("Process"),
                logListGridItem.listGrid.getField("MsgCode"),
                logListGridItem.listGrid.getField("DateLog"),
                logListGridItem.listGrid.getField("Msg"));
        logListGridItem.listGrid.fetchData();
    }

    private void sendEnv() {
        getEnvService.getEnvs(new AsyncCallback<ArrayList<Env>>() {
            @Override
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                com.google.gwt.user.client.Window.alert("Remote Procedure Call - Failure" + "\n"
                        + SERVER_ERROR + caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<Env> result) {
                if (result != null) {
                    envsRequest = new Env[result.size()];
                    result.toArray(envsRequest);
                    EnvDS envDS = new EnvDS(envsRequest);
                    setDataSourceEnvDS(envDS);
                    createEnvTree();
                    logCompareSection.setExpanded(true);
                } else {
                    com.google.gwt.user.client.Window.alert("Return is empty!");
                }
            }
        });
    }

    private void createEnvTree() {
        environmentTree = new EnvironmentTreeGrid(envsRequest);
      //  environmentTree.setAutoFetchData(true);
       // environmentTree.setShowAllRecords(true);
        envSection.setItems(environmentTree);
        envSection.setExpanded(true);
        environmentTree.addLeafClickHandler(new LeafClickHandler() {
            @Override
            public void onLeafClick(LeafClickEvent event) {
                serverName = event.getLeaf().getAttribute("SeverName");
                String date = configForm.dateIni.getEnteredValue();
                String timeAddBefore = configForm.timeAddBefore.getDisplayValue();
                String timeAddAfter = configForm.timeAddAfter.getDisplayValue();
                if (ehValid(date)) {
                    sendLog(serverName, 50, date, timeAddBefore, timeAddAfter);
                } else {
                    SC.say("Invalid range of Date/Time to query!", "Please, enter with a valid Date/Time on [Setting to Consult].");
                }
            }
        });
        itemDetailTabPane = new ItemDetailTabPane(logListGridItem,"LogScreen");
        itemDetailsSection.setItems(itemDetailTabPane);
        itemDetailTabPane.editorForm.getItem("saveItem").addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                sendLog(itemDetailTabPane.getLogKb());
            }
        });
    }

    private void showDetailSection() {
        itemDetailsSection.setHidden(false);
        itemDetailsSection.setExpanded(true);
        logListGridItem.listGrid.setHeight(300);
    }

    private void showAllSection() {
        leftSideLayout.setVisible(true);
        findSection.setHidden(false);
    }

    private void hideDetailSection() {
        itemDetailsSection.setExpanded(false);
        itemDetailsSection.setHidden(true);
        logListGridItem.listGrid.setHeight100();
    }

    private void hideAllSection() {
        leftSideLayout.setVisible(false);
        findSection.setHidden(true);
    }

    private boolean ehValid(String data) {
        if ("".equals(data)) {
            return false;
        } else {
            return true;
        }
    }
}