package br.com.etlplus.client.screen.centerlog;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class SearchKnowBase extends com.smartgwt.client.widgets.form.SearchForm {

    public ButtonItem findItem;
    TextItem tagsMsg;
    SelectItem cbMsgCode;
    SelectItem cbTools;
    SelectItem cbAnalyst;
    RadioGroupItem radioGroupItem;

    public SearchKnowBase(DataSource msgCodeDS, DataSource toolsDS, DataSource analystDS) {
        setTop(10);
        setCellPadding(6);
        setNumCols(15);
        setStyleName("defaultBorder");

        tagsMsg = new TextItem("Msg", "Msg Tags delimited by commas.");
        tagsMsg.setTitle("Msg");
        tagsMsg.setWidth(400);
        tagsMsg.setValue("Ex: Text tag 1, text tag 2, text tag 3...");
        tagsMsg.setCanEdit(true);
        tagsMsg.setVisible(false);


        findItem = new ButtonItem("FindMsg");
        findItem.setName("FindMsg");
        findItem.setTitle("Find");
        findItem.setIcon("find.png");
        findItem.setWidth(70);
        findItem.setEndRow(false);

        cbTools = new SelectItem("Tools");
        cbTools.setTitle("Tools");
        cbTools.setName("Tools");
        cbTools.setPickListWidth(250);
        cbTools.setVisible(false);
        cbTools.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                clearValue("MsgCode");
            }
        });
        cbTools.setOptionDataSource(toolsDS);
        cbMsgCode = new SelectItem("MsgCode") {
            @Override
            protected Criteria getPickListFilterCriteria() {
                String tools = (String) cbTools.getValue();
                Criteria criteria = new Criteria("Tools", tools);
                return criteria;
            }
        };
        cbMsgCode.setTitle("MsgCode");
        cbMsgCode.setName("MsgCode");
        cbMsgCode.setPickListWidth(250);
        cbMsgCode.setVisible(false);
        cbMsgCode.setOptionDataSource(msgCodeDS);

        cbAnalyst = new SelectItem("Analyst");
        cbAnalyst.setTitle("Analyst");
        cbAnalyst.setName("Analyst");
        cbAnalyst.setPickListWidth(250);
        cbAnalyst.setVisible(false);
        cbAnalyst.setOptionDataSource(analystDS);

        radioGroupItem = new RadioGroupItem();
        radioGroupItem.setTitle("Search Type");
        radioGroupItem.setName("typeSearch");
        radioGroupItem.setColSpan("*");
        radioGroupItem.setRequired(true);
        radioGroupItem.setVertical(true);
        radioGroupItem.setValueMap("MsgCode", "Tools", "Analyst", "Msg");

        radioGroupItem.setRedrawOnChange(true);
        radioGroupItem.setTitle("Choose a search type.");
        radioGroupItem.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                if ("Msg".equals(event.getItem().getDisplayValue())) {
                    tagsMsg.setVisible(true);
                    cbMsgCode.setVisible(false);
                    cbTools.setVisible(false);
                    cbAnalyst.setVisible(false);
                } else if ("MsgCode".equals(event.getItem().getDisplayValue())) {
                    cbTools.setVisible(true);
                    cbMsgCode.setVisible(true);
                    tagsMsg.setVisible(false);
                    cbAnalyst.setVisible(false);
                } else if ("Analyst".equals(event.getItem().getDisplayValue())) {
                    cbAnalyst.setVisible(true);
                    cbMsgCode.setVisible(false);
                    cbTools.setVisible(false);
                    tagsMsg.setVisible(false);
                } else if ("Tools".equals(event.getItem().getDisplayValue())) {
                    cbAnalyst.setVisible(false);
                    cbMsgCode.setVisible(false);
                    cbTools.setVisible(true);
                    tagsMsg.setVisible(false);
                }
            }
        });

        setItems(radioGroupItem, cbTools, cbMsgCode, cbAnalyst, tagsMsg, findItem);
    }

    public void addFindListener(ClickHandler handler) {
        findItem.addClickHandler(handler);
    }
}
