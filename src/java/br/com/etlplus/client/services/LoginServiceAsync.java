/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.services;

import br.com.etlplus.client.objects.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void authenticUser(String user, String password, AsyncCallback<User> callback)
			throws IllegalArgumentException;
}
