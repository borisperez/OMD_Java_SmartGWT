package br.com.etlplus.client.services;

import br.com.etlplus.client.objects.Log;
import br.com.etlplus.client.objects.LogKb;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;

@RemoteServiceRelativePath("log")
public interface LogService extends RemoteService {

    ArrayList<Log> getLogs(String server, int maxLog, String date, String dateAddBefore, String dateAddAfter) throws IllegalArgumentException;

    String setLog(LogKb logKb) throws IllegalArgumentException;

    ArrayList<LogKb> getLogsKb(LogKb logKb) throws IllegalArgumentException;

    ArrayList<LogKb> getDistinctCbValues() throws IllegalArgumentException;

    String updateLogKb(LogKb logKb) throws IllegalArgumentException;
}
