package br.com.etlplus.client.screen.centerlog;

import br.com.etlplus.client.screen.console.LogAnalysisConsole;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class CenterLogScreen extends VLayout {
    
    public Tab tabCenterLog;
    
    public CenterLogScreen(Tab tabCenterLog) throws IllegalStateException {
        
        TabSet tabSetCenterLog = new TabSet();
        tabSetCenterLog.setSize("100%", "100%");
        Tab tabLogSearch = new Tab("Log Search");
        Tab tabLogCompar = new Tab("Log Compar");
        LogAnalysisConsole logSearch = new LogAnalysisConsole();
        tabLogSearch.setPane(logSearch);
        tabSetCenterLog.addTab(tabLogSearch);
        tabSetCenterLog.addTab(tabLogCompar);
        tabCenterLog.setTabSet(tabSetCenterLog);
        setTab(tabCenterLog);
    }
    
    public Tab getTab() {
        return tabCenterLog;
    }
    
    public void setTab(Tab tab) {
        this.tabCenterLog = tab;
    }
}