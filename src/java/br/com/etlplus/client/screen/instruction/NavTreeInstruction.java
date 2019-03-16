package br.com.etlplus.client.screen.instruction;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class NavTreeInstruction extends TreeGrid {

    public NavTreeInstruction(DataSource instructionsDatasource) {
        setNodeIcon("file.png");
        setFolderIcon("folder.png");
        setWidth(200);
        setHeight(200);
        setShowOpenIcons(false);
        setClosedIconSuffix("");
        setShowDropIcons(false);
        setShowSelectedStyle(true);
        setShowPartialSelection(true);
        setCascadeSelection(false);
        setCanSort(false);
        setShowConnectors(true);
        setShowHeader(false);
        setLoadDataOnDemand(false);
        setSelectionType(SelectionStyle.SINGLE);
        setBorder("0px");
        setBodyStyleName("normal");
        setShowHeader(false);
        setDataSource(instructionsDatasource);



    }
}
