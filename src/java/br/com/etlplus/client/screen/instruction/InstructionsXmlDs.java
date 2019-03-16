/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.screen.instruction;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;
public class InstructionsXmlDs extends DataSource {
    private static InstructionsXmlDs instance = null;

    public static InstructionsXmlDs getInstance() {
        if (instance == null) {
            instance = new InstructionsXmlDs("instructionsXmlDs");
        }
        return instance;
    }

    public InstructionsXmlDs(String id) {

        setID(id);
   //     setDataFormat(DSDataFormat.XML);
        setRecordXPath("/List/instructions");


        DataSourceTextField itemNameField = new DataSourceTextField("instruction", "Item", 128, true);
        itemNameField.setPrimaryKey(true);

        DataSourceTextField parentField = new DataSourceTextField("parentID", null);
        FloatRangeValidator rangeValidator = new FloatRangeValidator();
        rangeValidator.setMin(0);
        rangeValidator.setErrorMessage("Please enter a valid (positive) cost");
        parentField.setHidden(true);
        parentField.setRequired(true);
        parentField.setRootValue("root");
        parentField.setForeignKey("instructionsXmlDs.instruction");


        setFields(itemNameField, parentField);
        setDataURL("ds/data/instructions.data.xml");

        
        setClientOnly(true);

    }
    
}
