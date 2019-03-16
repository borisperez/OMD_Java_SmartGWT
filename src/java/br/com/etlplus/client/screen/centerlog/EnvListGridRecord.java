package br.com.etlplus.client.screen.centerlog;

import br.com.etlplus.client.objects.Env;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EnvListGridRecord {

    private static ListGridRecord[] records;

    public EnvListGridRecord(Env[] envs) {
        setNewRecords(envs);
    }

    public static ListGridRecord[] getRecords() {
        if (records != null) {
            return records;
        } else {
            return null;
        }
    }

    public static ListGridRecord createRecord(Env env) {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute("EnvId", env.getEnvId());
        record.setAttribute("EnvParentId", env.getEnvParentId());
        record.setAttribute("SeverName", env.getSeverName());
        record.setAttribute("EnvironmentName", env.getEnvironmentName());
        return record;
    }

    public static ListGridRecord[] setNewRecords(Env[] envs) {
        if (envs != null) {
            records = new ListGridRecord[envs.length];
            for (int i = 0; i < envs.length; i++) {
                records[i] = createRecord(envs[i]);
            }
        } else {
            return null;
        }
        return records;
    }
}
