/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.services;

import br.com.etlplus.client.objects.Env;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;

public interface EnvServiceAsync {
	void getEnvs(AsyncCallback<ArrayList<Env>> callback)
			throws IllegalArgumentException;
}
