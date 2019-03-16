/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
//import com.smartgwt.client.widgets.grid.ListGridRecord;

public class LogDSKb extends DataSource {

    public LogDSKb(String type) {

        DataSourceIntegerField logIdField = new DataSourceIntegerField("LogId", "LogId");
        DataSourceTextField toolsField = new DataSourceTextField("Tools", "Tools");
        DataSourceTextField processField = new DataSourceTextField("Process", "Process");
        DataSourceTextField calledField = new DataSourceTextField("Called", "Called");
        DataSourceTextField analystField = new DataSourceTextField("Analyst", "Analyst");
        DataSourceTextField environmentNameField = new DataSourceTextField("EnvironmentName", "EnvironmentName");
        DataSourceTextField environmentTypeField = new DataSourceTextField("EnvironmentType", "EnvironmentType");
        DataSourceTextField msgCodeField = new DataSourceTextField("MsgCode", "MsgCode");
        DataSourceIntegerField envIdField = new DataSourceIntegerField("EnvId", "EnvId");
        //  EnvIdField.setHidden(true);
        DataSourceTextField dateLoadField = new DataSourceTextField("DateLog", "DateLog");
        DataSourceTextField dateField = new DataSourceTextField("Date", "Date");
        DataSourceTextField timeField = new DataSourceTextField("Time", "Time");
        DataSourceTextField statusField = new DataSourceTextField("Status", "Status");
        DataSourceTextField whoIsField = new DataSourceTextField("WhoIs", "WhoIs");
        //  DateLoadField.setHidden(true);
        DataSourceTextField msgField = new DataSourceTextField("Msg", "Msg");
        DataSourceTextField resolutionField = new DataSourceTextField("Resolution_Obs", "Resolution_Obs");
        if ("LogScreen".equals(type)) {
            setFields(logIdField, toolsField, dateLoadField, processField, environmentNameField, environmentTypeField, msgCodeField, msgField, envIdField);
        } else if ("LogKBScreen".equals(type)) {
            setFields(logIdField, toolsField, processField, calledField, analystField, environmentNameField, environmentTypeField,  msgCodeField, envIdField, dateField, timeField, statusField, whoIsField, msgField, resolutionField);
        }
        setClientOnly(true);

    }
}
