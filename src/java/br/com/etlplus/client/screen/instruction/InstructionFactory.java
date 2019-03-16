package br.com.etlplus.client.screen.instruction;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;

public class InstructionFactory extends VLayout {

    public InstructionFactory(String name) {
        String Instruction = null;
        String Register =
                "<span style='font-size:22px;'>Register</span> "
                + "<b>A</b>Environment<b>C</b>dastro <b>D</b>e <b>A</b>mbientes "
                + "Pagina para cadstro de ambientes <b></b>. "
                + "tttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "
                + "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttt <b>tttttt</b>, <b>ttttttt</b>, "
                + "eeeeeee <b>rrrrrrrrrrr</b>.<br>"
                + "";
        String Draw =
                "<span style='font-size:22px;'>Draw</span> "
                + "<b>A</b>Environment<b>C</b>dastro <b>D</b>e <b>A</b>mbientes "
                + "Pagina para desenhar os ambientes <b></b>. "
                + "tttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "
                + "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttt <b>tttttt</b>, <b>ttttttt</b>, "
                + "eeeeeee <b>rrrrrrrrrrr</b>.<br>"
                + "";
        String Edit =
                "<span style='font-size:22px;'>Edit</span> "
                + "<b>A</b>Environment<b>C</b>dastro <b>D</b>e <b>A</b>mbientes "
                + "Pagina para editar os cadstro de ambientes <b></b>. "
                + "tttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "
                + "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttt <b>tttttt</b>, <b>ttttttt</b>, "
                + "eeeeeee <b>rrrrrrrrrrr</b>.<br>"
                + "";
        String Create =
                "<span style='font-size:22px;'>Create</span> "
                + "<b>A</b>User<b>C</b>dastro <b>D</b>e <b>U</b>suarios "
                + "Pagina para cadstro de usuarios <b></b>. "
                + "tttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "
                + "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttt <b>tttttt</b>, <b>ttttttt</b>, "
                + "eeeeeee <b>rrrrrrrrrrr</b>.<br>"
                + "";
        String Permission =
                "<span style='font-size:22px;'>Permission</span> "
                + "<b>A</b>User<b>C</b>dastro <b>D</b>e <b>U</b>suarios "
                + "Pagina para dar permissoes de usuarios <b></b>. "
                + "tttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "
                + "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"
                + "tttttttttttttttttttttt <b>tttttt</b>, <b>ttttttt</b>, "
                + "eeeeeee <b>rrrrrrrrrrr</b>.<br>"
                + "";

        if ("Register".equals(name)) {
            Instruction = Register;
        }
        if ("Draw".equals(name)) {
            Instruction = Draw;
        }
        if ("Edit".equals(name)) {
            Instruction = Edit;
        }
        if ("Create".equals(name)) {
            Instruction = Create;
        }
        if ("Permission".equals(name)) {
            Instruction = Permission;
        }

        setMembersMargin(5);
        final Canvas htmlCanvas = new Canvas();
        htmlCanvas.setHeight100();
        htmlCanvas.setPadding(2);
        htmlCanvas.setOverflow(Overflow.HIDDEN);
        htmlCanvas.setCanDragResize(true);
        htmlCanvas.setShowEdges(true);
        htmlCanvas.setContents(Instruction);

//        final RichTextEditor richTextEditor = new RichTextEditor();  
//        richTextEditor.setHeight(155);  
//        richTextEditor.setOverflow(Overflow.HIDDEN);  
//        richTextEditor.setCanDragResize(true);  
//        richTextEditor.setShowEdges(true);  

        // Standard control group options include  
        // "fontControls", "formatControls", "styleControls" and "colorControls"  
        //richTextEditor.setControlGroups(new String[]{"fontControls", "styleControls"});  
        //     richTextEditor.setValue(ajaxDefinition);  
        //     layout.addMember(richTextEditor);  

        //      IButton button = new IButton("Set Canvas HTML");  
        //     button.setWidth(150);  
        //    button.addClickHandler(new ClickHandler() {  
        //       public void onClick(ClickEvent event) {  
        //          htmlCanvas.setContents(richTextEditor.getValue());  
        //     }  
        //  });  

        //       layout.addMember(button);  
        addMember(htmlCanvas);



    }
}
