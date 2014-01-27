package com.probojnik.AeroGlyph;

import android.os.AsyncTask;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* author probojnik.com
*/
public class MyAsyncTask extends AsyncTask<Void, Void, List<MarkerBean>> {
    IAsyncTask ctx;

    public MyAsyncTask(IAsyncTask ctx) {
       this.ctx = ctx;
    }

    @Override
    protected List<MarkerBean> doInBackground(Void... params) {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(Consts.URL));
            config.setEnabledForExtensions(false);
            config.setConnectionTimeout(60 * 1000);
            config.setReplyTimeout(60 * 1000);

            XmlRpcClient client = new XmlRpcClient();
            client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
            client.setConfig(config);

            Map<String,String> map = new HashMap<String, String>();
            map.put(Consts.NAME, Consts.VALUE);

            Object[] response = (Object[]) client.execute(Consts.METHOD, new Object [] {map});

            return Utils.obj2Bean(response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<MarkerBean> l) {
        super.onPostExecute(l);
        if(l != null) ctx.cbAddMarkers(l);
    }
}
