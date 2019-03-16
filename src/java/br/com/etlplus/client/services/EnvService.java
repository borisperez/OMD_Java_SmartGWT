/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.services;

import br.com.etlplus.client.objects.Env;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;

    @RemoteServiceRelativePath("env")
    public interface EnvService extends RemoteService {
        ArrayList<Env> getEnvs() throws IllegalArgumentException;
    }