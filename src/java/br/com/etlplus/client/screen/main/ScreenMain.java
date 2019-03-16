package br.com.etlplus.client.screen.main;

import br.com.etlplus.client.screen.console.LogAnalysisConsole;
import br.com.etlplus.client.screen.instruction.HeaderArea;
import br.com.etlplus.client.screen.instruction.NavTreeInstruction;
import br.com.etlplus.client.objects.User;
//import br.com.etlplus.client.screen.centerlog.CenterLogScreen;
//import br.com.etlplus.client.screen.centerlog.LogSearch;
import br.com.etlplus.client.screen.instruction.InstructionScreen;
//import br.com.etlplus.client.screen.instruction.InstructionScreen;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
//import java.util.HashMap;

public class ScreenMain extends VLayout {

    private VLayout header;
    //final String skinCookieName = "skin_name_2_4";
    //   private HashMap hashScreen = new HashMap();
    String roles[];
    HLayout mainLayout;
    HLayout southLayout;
    public static HLayout eastLayout;
    HLayout westLayout;
    NavTreeInstruction navTree;
    public static TabSet tabSetMain;
    InstructionScreen instScreen;

    public ScreenMain(User user) {
        setSize("100%", "100%");
        setTitle("OMD Application");
        setCanDragReposition(false);
        setMembersMargin(5);
        setBackgroundImage("backuground_omd4.png");
        setBackgroundRepeat(BackgroundRepeat.NO_REPEAT);
        setBackgroundColor("#2d477b");
        header = new HeaderArea(user);
        southLayout = new HLayout();
        southLayout.setMembers(header);
        addMember(southLayout);
        tabSetMain = new TabSet();
        tabSetMain.setSize("100%", "100%");
        createTabfromUser(user);

        addMember(tabSetMain);
        this.show();
    }

    private void createTabfromUser(User user) {
        roles = user.getRole().toString().split("\\|");
        for (int i = 0; i < roles.length; i++) {
            String string = roles[i];
            if ("administrator".equals(string)) {
                mountLogCenter();
                tabSetMain.addTab(new Tab("Home"));
                tabSetMain.addTab(new Tab("Administrator"));
                tabSetMain.addTab(new Tab("Monitor/Alert"));
                tabSetMain.addTab(new Tab("Process QA"));
                tabSetMain.addTab(new Tab("Report"));
                tabSetMain.addTab(new Tab("Proj Documentation"));
                tabSetMain.addTab(new Tab("Scheduller"));
                mountIntructions();
            }
            if ("operator".equals(string)) {
                tabSetMain.addTab(new Tab("Home"));
                tabSetMain.addTab(new Tab("Monitor/Alert"));
                tabSetMain.addTab(new Tab("Report"));
                tabSetMain.addTab(new Tab("Proj Documentation"));
                tabSetMain.addTab(new Tab("Scheduller"));
                mountIntructions();
            }
            if ("project".equals(string)) {
                tabSetMain.addTab(new Tab("Home"));
                tabSetMain.addTab(new Tab("Report"));
                tabSetMain.addTab(new Tab("Proj Documentation"));
                mountIntructions();
            }
            if ("developer".equals(string)) {
                tabSetMain.addTab(new Tab("Home"));
                tabSetMain.addTab(new Tab("Process QA"));
                tabSetMain.addTab(new Tab("Report"));
                tabSetMain.addTab(new Tab("Log Center"));
                tabSetMain.addTab(new Tab("Proj Documentation"));
                mountIntructions();
            }
        }

    }
//    private void loadTbScreen(User user) {
//        roles = user.getRole().toString().split("\\|");
//        for (int i = 0; i < roles.length; i++) {
//            if (!"administrator".equals(roles[i])) {
//                if ("operator".equals(roles[i])) {
//                    for (int o = 0; o < RolesScreen.operator.length; o++) {
//                        hashScreen.put(RolesScreen.operator[o], "operator");
//                    }
//                }
//                if ("architect".equals(roles[i])) {
//                    for (int a = 0; a < RolesScreen.architect.length; a++) {
//                        hashScreen.put(RolesScreen.architect[a], "architect");
//                    }
//                }
//                if ("project".equals(roles[i])) {
//                    for (int p = 0; p < RolesScreen.project.length; p++) {
//                        hashScreen.put(RolesScreen.project[p], "project");
//                    }
//                }
//                if ("developer".equals(roles[i])) {
//                    if ("developer".equals(roles[i])) {
//                        for (int d = 0; d < RolesScreen.developer.length; d++) {
//                            hashScreen.put(RolesScreen.developer[d], "developer");
//                        }
//                    }
//                }
//            }
//            hashScreen.put("administrator", "administrator");
//        }
//    }

    private void mountLogCenter() throws IllegalStateException {
        Tab tabLogCenter = new Tab("Log Center");
        // CenterLogScreen centerLogScrenn = new CenterLogScreen(tabLogCenter);
        LogAnalysisConsole logSearch = new LogAnalysisConsole();
        tabLogCenter.setPane(logSearch);
        tabSetMain.addTab(tabLogCenter);
    }

    private void mountIntructions() throws IllegalStateException {
        Tab tabInstruction = new Tab("Instructions");
        instScreen = new InstructionScreen(tabInstruction);
        tabInstruction.setID("Instructions");
        tabSetMain.addTab(instScreen.getTabInstruction());
    }
}
