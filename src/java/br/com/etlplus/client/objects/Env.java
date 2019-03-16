package br.com.etlplus.client.objects;

import com.nkdata.gwt.streamer.client.Streamable;
import java.util.ArrayList;

public final class Env implements Streamable {

    private String SeverName, EnvironmentName, TipoEnv, ServerFunction, TypeContingency;
    private boolean consistencia;
    private int EnvId, EnvParentId;

    public Env() {
        super();
        setEnvId(0);
        setEnvParentId(0);
        setSeverName("");
        setEnvironmentName("");
    }

    public ArrayList<String> getCampos() {
        ArrayList<String> Campos;
        Campos = new ArrayList<String>();
        Campos.add("EnvId");
        Campos.add("EnvParentId");
        Campos.add("SeverName");
        Campos.add("EnvironmentName");
        return Campos;
    }

    public int getEnvId() {
        return EnvId;
    }

    public void setEnvId(int EnvId) {
        this.EnvId = EnvId;
    }

    public int getEnvParentId() {
        return EnvParentId;
    }

    public void setEnvParentId(int EnvParentId) {
        this.EnvParentId = EnvParentId;
    }

    public String getSeverName() {
        return SeverName;
    }

    public void setSeverName(String SeverName) {
        this.SeverName = SeverName;
    }

    public String getEnvironmentName() {
        return EnvironmentName;
    }

    public void setEnvironmentName(String EnvironmentName) {
        this.EnvironmentName = EnvironmentName;
    }

    public void setConsistencia(boolean Consistencia) {
        this.consistencia = Consistencia;
    }

    public boolean getConsistencia() {
        return consistencia;
    }
}