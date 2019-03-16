/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import br.com.etlplus.client.objects.Env;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class EnvDS extends DataSource {

    public EnvDS(Env[] envs) {
        EnvListGridRecord envLGRecord = new EnvListGridRecord(envs);
        DataSourceIntegerField envIdField = new DataSourceIntegerField("EnvId", "EnvId");
        envIdField.setHidden(true);
        envIdField.setPrimaryKey(true);
        DataSourceIntegerField envParentIdField = new DataSourceIntegerField("EnvParentId", "EnvParentId");
        envParentIdField.setHidden(true);
        DataSourceTextField severNameField = new DataSourceTextField("SeverName", "SeverName");
        DataSourceTextField environmentNameField = new DataSourceTextField("EnvironmentName", "EnvironmentName");
        setFields(envIdField, envParentIdField, severNameField, environmentNameField);
        setTestData(envLGRecord.getRecords());
        setClientOnly(true);
    }
}
