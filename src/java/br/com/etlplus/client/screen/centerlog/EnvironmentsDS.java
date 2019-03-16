/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.centerlog;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class EnvironmentsDS extends DataSource {

    private static EnvironmentsDS instance = null;

    public static EnvironmentsDS getInstance() {
        if (instance == null) {
            instance = new EnvironmentsDS("supplyCategoryDS");
        }
        return instance;
    }

    public EnvironmentsDS(String id) {

        setID(id);
        setRecordXPath("/List/environments");


        DataSourceTextField itemNameField = new DataSourceTextField("environment", "Item", 128, true);
        itemNameField.setPrimaryKey(true);

        DataSourceTextField parentField = new DataSourceTextField("parentID", null);
        parentField.setHidden(true);
        parentField.setRequired(true);
        parentField.setRootValue("root");
        parentField.setForeignKey("supplyCategoryDS.categoryName");
        DataSourceTextField serverField = new DataSourceTextField("server", null);
        serverField.setHidden(true);


        setFields(itemNameField, parentField, serverField);

        setDataURL("ds/data/logEnvironment.data.xml");
        
        setClientOnly(true);

    }
}
