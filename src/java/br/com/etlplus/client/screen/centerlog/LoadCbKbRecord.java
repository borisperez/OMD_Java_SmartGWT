/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import br.com.etlplus.client.objects.LogKb;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import java.util.ArrayList;
import java.util.HashMap;

public class LoadCbKbRecord {

    private static ListGridRecord[] recordsMsgCode;
    private static ListGridRecord[] recordsTools;
    private static ListGridRecord[] recordsAnalyst;
    public static ListGridRecord[] records;
    public static int count = 0;

    public LoadCbKbRecord() {
        //   setNewRecords(logs);
    }

    public static ListGridRecord[] getRecordsMsgCode() {
        return recordsMsgCode;
    }

    public static void setRecordsMsgCode(ListGridRecord[] aRecordsMsgCode) {
        recordsMsgCode = aRecordsMsgCode;
    }

    public static ListGridRecord[] getRecordsTools() {
        return recordsTools;
    }

    public static void setRecordsTools(ListGridRecord[] aRecordsTools) {
        recordsTools = aRecordsTools;
    }

    public static ListGridRecord[] getRecordsAnalyst() {
        return recordsAnalyst;
    }

    public static void setRecordsAnalyst(ListGridRecord[] aRecordsAnalyst) {
        recordsAnalyst = aRecordsAnalyst;
    }

    public static void setTypeRecords(ArrayList<LogKb> logs) {
        HashMap<String, ListGridRecord> listaMsgCode = new HashMap<String, ListGridRecord>();
        HashMap<String, ListGridRecord> listaTools = new HashMap<String, ListGridRecord>();
        HashMap<String, ListGridRecord> listaAnalyst = new HashMap<String, ListGridRecord>();
        if (logs != null) {
            for (int i = 0; i < logs.size(); i++) {

                if (logs.get(i).getMsgCode() != null) {
                    String msg = logs.get(i).getMsgCode();
                    int largura = logs.get(i).getMsgCode().replace(" ", "").length();
                    if (!"".equals(logs.get(i).getMsgCode()) || logs.get(i).getMsgCode().replace(" ", "").length() > 0) {
                        listaMsgCode.put(logs.get(i).getMsgCode() + "_" + logs.get(i).getTools(), createRecordMsg(logs.get(i)));
                    }
                }
                if (logs.get(i).getAnalyst() != null) {
                    if (!"".equals(logs.get(i).getAnalyst()) || logs.get(i).getAnalyst().replace(" ", "").length() > 0) {
                        listaAnalyst.put(logs.get(i).getAnalyst(), createRecordAnalyst(logs.get(i)));
                    }
                }
                listaTools.put(logs.get(i).getTools(), createRecordTools(logs.get(i)));
            }

            setRecordsMsgCode(new ListGridRecord[listaMsgCode.size()]);
            listaMsgCode.values().toArray(getRecordsMsgCode());
            setRecordsTools(new ListGridRecord[listaTools.size()]);
            listaTools.values().toArray(getRecordsTools());
            setRecordsAnalyst(new ListGridRecord[listaAnalyst.size()]);
            listaAnalyst.values().toArray(getRecordsAnalyst());
        }
    }

    public static void cleanRecords() {
        records = new ListGridRecord[0];
    }

    public static ListGridRecord createRecordMsg(LogKb log) {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute("Tools", log.getTools());
        record.setAttribute("MsgCode", log.getMsgCode());
        return record;
    }

    public static ListGridRecord createRecordTools(LogKb log) {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute("Tools", log.getTools());
        return record;
    }

    public static ListGridRecord createRecordAnalyst(LogKb log) {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute("Analyst", log.getAnalyst());
        return record;
    }
//    public static ListGridRecord createRecord(Log log) {
//        ListGridRecord record = new ListGridRecord();
//        record.setAttribute("LogId", log.getLogId());
//        record.setAttribute("Tools", log.getTools());
//        record.setAttribute("Process", log.getProcess());
//        record.setAttribute("EnvironmentName", log.getEnvironmentName());
//        record.setAttribute("EnvironmentType", log.getEnvironmentType());
//        record.setAttribute("MsgCode", log.getMsgCode());
//        record.setAttribute("EnvId", log.getEnvId());
//        record.setAttribute("Msg", log.getMsg());
//        record.setAttribute("DateLoad", log.getDateLoad());
//        return record;
//    }
}
