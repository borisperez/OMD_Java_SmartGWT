package br.com.etlplus.client.screen.instruction;

import br.com.etlplus.client.screen.instruction.NavTreeInstruction;
import br.com.etlplus.client.screen.instruction.InstructionsXmlDs;
import br.com.etlplus.client.screen.instruction.InstructionFactory;
import br.com.etlplus.client.screen.main.ScreenMain;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class NavigationArea extends HLayout {

    private NavTreeInstruction navTreeInstruction;

    public NavigationArea() {

        super();
        DataSource instructions = InstructionsXmlDs.getInstance();
        navTreeInstruction = new NavTreeInstruction(instructions);
        navTreeInstruction.setAutoFetchData(true);
        navTreeInstruction.addNodeClickHandler(new NodeClickHandler() {
            @Override
            public void onNodeClick(NodeClickEvent event) {
                String name = event.getNode().getAttribute("instruction");
                HLayout layout = null;
                Canvas[] panes;
                panes = ScreenMain.tabSetMain.getTab("Instructions").getPane().getChildren();
                for (int i = 0; i < panes.length; i++) {
                    if (panes[i].getID().equals("eastLayoutInst")) {
                        layout = (HLayout) panes[i];
                        Canvas[] members = layout.getMembers();
                        for (int j = 0; j < members.length; j++) {
                            layout.removeMember(members[j]);
                        }
                    }
                }
                layout.addMember(new InstructionFactory(name));
            }
        });
        this.setMembersMargin(20);
        this.setOverflow(Overflow.HIDDEN);
        this.setShowResizeBar(true);
        //    this.setBorder("2px graphite");
        this.setMargin(5);
        final SectionStack sectionStack = new SectionStack();
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setShowExpandControls(true);
        sectionStack.setAnimateSections(true);
        sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
        sectionStack.setOverflow(Overflow.HIDDEN);

        SectionStackSection sectionAdm = new SectionStackSection("Administration");
        sectionAdm.setExpanded(true);
        sectionAdm.setResizeable(true);
        sectionAdm.setItems(navTreeInstruction);

        SectionStackSection section2 = new SectionStackSection("Documentation");
        section2.setExpanded(false);
        //Label label2 = new Label();
        //label2.setContents("Label2");
        //label2.setOverflow(Overflow.AUTO);
        //label2.setPadding(10);
        //section2.addItem(label2);

        SectionStackSection section3 = new SectionStackSection("Quality Assurance");
        section3.setExpanded(false);
        //Label label3 = new Label();
        //label3.setContents("Label3");
        //label3.setOverflow(Overflow.AUTO);
        //label3.setPadding(10);
        //section3.addItem(label3);

        sectionStack.addSection(sectionAdm);
        sectionStack.addSection(section2);
        sectionStack.addSection(section3);

        this.addMember(sectionStack);

    }

    public void removeCanvasMembers(Canvas[] canvas) {
        for (int i = 0; i < canvas.length; i++) {
            ScreenMain.eastLayout.removeMember(canvas[i]);
        }

    }
}
