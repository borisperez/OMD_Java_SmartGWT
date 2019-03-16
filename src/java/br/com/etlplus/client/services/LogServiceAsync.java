/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.services;

import br.com.etlplus.client.objects.Log;
import br.com.etlplus.client.objects.LogKb;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;

public interface LogServiceAsync {

    void getLogs(String server, int maxLog, String date, String dateAddBefore, String dateAddAfter, AsyncCallback<ArrayList<Log>> callback) throws IllegalArgumentException;

    void setLog(LogKb logKb, AsyncCallback<String> callback) throws IllegalArgumentException;

    void getLogsKb(LogKb logKb, AsyncCallback<ArrayList<LogKb>> callback) throws IllegalArgumentException;

    void getDistinctCbValues(AsyncCallback<ArrayList<LogKb>> callback) throws IllegalArgumentException;

    void updateLogKb(LogKb logKb, AsyncCallback<String> callback) throws IllegalArgumentException;
}
