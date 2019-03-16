/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import br.com.etlplus.client.objects.Log;
import br.com.etlplus.client.objects.LogKb;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import java.util.HashMap;

public class LogListGridRecord {

    public static ListGridRecord[] records;
    public static ListGridRecord[] recordsKb;
    public static HashMap<Integer, ListGridRecord> lista = new HashMap<Integer, ListGridRecord>();
    public static HashMap<Integer, ListGridRecord> listaKb = new HashMap<Integer, ListGridRecord>();
    public static int count = 0;
    //  private static Log[] logsRequest;

    public LogListGridRecord(Log[] logs) {
        //   setNewRecords(logs);
    }

    public static ListGridRecord[] getRecords() {
        if (records != null) {
            return records;
        } else {
            return null;
        }
    }

    public static ListGridRecord[] getRecordsKb() {
        if (recordsKb != null) {
            return recordsKb;
        } else {
            return null;
        }
    }

    public static ListGridRecord createRecord(Object logType) {
        ListGridRecord record = new ListGridRecord();
        if (logType instanceof LogKb) {
       //     LogKb logkb = new LogKb();
            LogKb logkb = (LogKb) logType;
            int testlog = logkb.getLogKbId();
            record.setAttribute("LogId", logkb.getLogKbId());
            record.setAttribute("Tools", logkb.getTools());
            record.setAttribute("Process", logkb.getProcess());
            record.setAttribute("Called", logkb.getCalled());
            record.setAttribute("Analyst", logkb.getAnalyst());
            record.setAttribute("EnvironmentName", logkb.getEnvironmentName());
            record.setAttribute("EnvironmentType", logkb.getEnvironmentType());
            record.setAttribute("MsgCode", logkb.getMsgCode());
            record.setAttribute("EnvId", logkb.getEnvId());
            record.setAttribute("Date", logkb.getDate());
            record.setAttribute("Time", logkb.getTime());
            record.setAttribute("Status", logkb.getStatus());
            record.setAttribute("WhoIs", logkb.getWhoIs());
            record.setAttribute("Msg", logkb.getMsg());
            record.setAttribute("Resolution_Obs", logkb.getResolution_Obs());
        } else if (logType instanceof Log) {
            Log log = (Log) logType;
            record.setAttribute("LogId", log.getLogId());
            record.setAttribute("Tools", log.getTools());
            record.setAttribute("Process", log.getProcess());
            record.setAttribute("EnvironmentName", log.getEnvironmentName());
            record.setAttribute("EnvironmentType", log.getEnvironmentType());
            record.setAttribute("MsgCode", log.getMsgCode());
            record.setAttribute("EnvId", log.getEnvId());
            record.setAttribute("Msg", log.getMsg());
            record.setAttribute("DateLog", log.getDateLog());
        }
        return record;
    }

    public static ListGridRecord[] setNewRecords(Object[] logs) {
        if (logs != null) {
            if (logs[0] instanceof LogKb) {
                for (int i = 0; i < logs.length; i++) {
                    LogKb log = (LogKb) logs[i];
                    listaKb.put(log.getLogKbId(), createRecord(log));
                }
                recordsKb = new ListGridRecord[listaKb.size()];
                listaKb.values().toArray(recordsKb);
            } else if (logs[0] instanceof Log) {
                for (int i = 0; i < logs.length; i++) {
                    Log log = (Log) logs[i];
                    lista.put(log.getLogId(), createRecord(log));
                }
                records = new ListGridRecord[lista.size()];
                lista.values().toArray(records);
            }
        } else {
            return null;
        }
        return records;
    }

    public static void cleanRecords() {
        records = new ListGridRecord[0];
    }
}
