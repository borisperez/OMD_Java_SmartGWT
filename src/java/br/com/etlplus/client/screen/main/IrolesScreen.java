package br.com.etlplus.client.screen.main;


public interface IrolesScreen {
    String[] operator = {"MonitorQuery","CleanQuery","EnvAvailable","LogsSeek","LogSearch","Knowledgebase","ProjectDocumentationQuery","JobsDocumentationQuery"};
    String[] architect = {"EnvDrawing","ProjectDocumentation","JobsDocumentation","ProjectDocumentationQuery","JobsDocumentationQuery"};
    String[] project = {"ProjectDocumentation","JobsDocumentation","ProjectDocumentationQuery","JobsDocumentationQuery"};
    String[] developer = {"QARequest","QACad","QAQuery","QAEdit","EnvDashboard","TimelineReport","EnvAvailableReport","TopJobsReport","LogsSeek","LogSearch","Knowledgebase"};
}
