package br.com.etlplus.client.objects;

import java.util.ArrayList;
import com.nkdata.gwt.streamer.client.Streamable;

public class Log implements Streamable {

    private String EnvironmentName, EnvironmentType, MsgCode, Tools, Process, DateLog, Msg;
    private boolean consistencia;
    private int LogId, EnvId;

    public Log() {
        super();
        setEnvironmentName("");
        setEnvironmentType("");
        setMsgCode("");
        setTools("");
        setProcess("");
        setDateLog("");
        setMsg("");
    }

    public void setConsistencia(boolean Consistencia) {
        this.consistencia = Consistencia;
    }

    public boolean getConsistencia() {
        return consistencia;
    }

    public int getLogId() {
        return LogId;
    }

    public void setLogId(int LogId) {
        this.LogId = LogId;
    }

    public String getEnvironmentName() {
        return EnvironmentName;
    }

    public void setEnvironmentName(String EnvironmentName) {
        this.EnvironmentName = EnvironmentName;
    }

    public String getEnvironmentType() {
        return EnvironmentType;
    }

    public void setEnvironmentType(String EnvironmentType) {
        this.EnvironmentType = EnvironmentType;
    }

    public String getMsgCode() {
        return MsgCode;
    }

    public void setMsgCode(String MsgCode) {
        this.MsgCode = MsgCode;
    }

    public String getTools() {
        return Tools;
    }

    public void setTools(String Tools) {
        this.Tools = Tools;
    }

    public String getProcess() {
        return Process;
    }

    public void setProcess(String Process) {
        this.Process = Process;
    }

    public String getDateLog() {
        return DateLog;
    }

    public void setDateLog(String DateLoad) {
        this.DateLog = DateLoad;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getEnvId() {
        return EnvId;
    }

    public void setEnvId(int EnvId) {
        this.EnvId = EnvId;
    }
    
    public ArrayList<String> getCampos() {
        ArrayList<String> Campos;
        Campos = new ArrayList<String>();
        Campos.add("LogId");
        Campos.add("EnvironmentName");
        Campos.add("EnvironmentType");
        Campos.add("MsgCode");
        Campos.add("Tools");
        Campos.add("Process");
        Campos.add("DateLog");
        Campos.add("Msg");
        Campos.add("EnvId");
        return Campos;
    }
}
