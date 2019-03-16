
package br.com.etlplus.client.services;

import br.com.etlplus.client.objects.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	User authenticUser(String user, String password) throws IllegalArgumentException;
}
