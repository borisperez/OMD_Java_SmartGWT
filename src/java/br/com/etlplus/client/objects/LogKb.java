package br.com.etlplus.client.objects;

import java.util.ArrayList;

public class LogKb extends Log {

    private String Status, WhoIs, Called, Analyst, Resolution_Obs, campoTag, Date, time;
    private int LogKbId;

    public LogKb() {
        setLogId(0);
        setStatus("");
        setCalled("");
        setAnalyst("");
        setResolution_Obs("");
        setStatus("");
        setWhoIs("");
        setTime("");
        setDate("");
        setTime("");
    }

    public int getLogKbId() {
        return LogKbId;
    }

    public void setLogKbId(int aLogKbId) {
        LogKbId = aLogKbId;
    }

    public String getCalled() {
        return Called;
    }

    public void setCalled(String called) {
        Called = called;
    }

    public String getAnalyst() {
        return Analyst;
    }

    public void setAnalyst(String analyst) {
        Analyst = analyst;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getResolution_Obs() {
        return Resolution_Obs;
    }

    public void setResolution_Obs(String resolution_Obs) {
        Resolution_Obs = resolution_Obs;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String aStatus) {
        Status = aStatus;
    }

    public String getWhoIs() {
        return WhoIs;
    }

    public void setWhoIs(String WhoIs) {
        this.WhoIs = WhoIs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getCampos() {
        ArrayList<String> Campos;
        Campos = new ArrayList<String>();
        Campos.add("LogKbId");
        Campos.add("EnvironmentName");
        Campos.add("EnvironmentType");
        Campos.add("MsgCode");
        Campos.add("Called");
        Campos.add("Tools");
        Campos.add("Process");
        Campos.add("Analyst");
        Campos.add("Date");
        Campos.add("Msg");
        Campos.add("Resolution_Obs");
        Campos.add("Status");
        Campos.add("WhoIs");
        Campos.add("Time");
        Campos.add("EnvId");
        return Campos;
    }


}
