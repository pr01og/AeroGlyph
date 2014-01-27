package com.probojnik.AeroGlyph;

import android.util.Log;

import java.util.*;

/**
 * author probojnik.com
 */
public class Utils {

    static List<MarkerBean> obj2Bean(Object[] par){

        List<MarkerBean> res = new ArrayList<MarkerBean>();
        for(Object o: par){
            if(o instanceof HashMap){
                HashMap<String, Object> hm = (HashMap) o;

                try {
                    MarkerBean mb = new MarkerBean(
                            (String) hm.get(MarkerBean.MB.CATID.v),
                            (String) hm.get(MarkerBean.MB.CUSID.v),
                            Integer.parseInt( (String) hm.get(MarkerBean.MB.STATE.v) ),
                            Double.parseDouble( (String) hm.get(MarkerBean.MB.LONG.v) ),
                            Double.parseDouble( (String) hm.get(MarkerBean.MB.LAT.v) ),
                            (Integer) hm.get(MarkerBean.MB.CODE.v),
                            (String) hm.get(MarkerBean.MB.POSID.v)
                    );
                    Log.d("log123", "add HashMap STATE-" +  hm.get(MarkerBean.MB.STATE.v) + " LONG-" + hm.get(MarkerBean.MB.LONG.v) + " LAT-" +  hm.get(MarkerBean.MB.LAT.v) );
                    res.add(mb);
                } catch(NumberFormatException e) {
                    Log.e("log123", "NumberFormatException STATE-" +  hm.get(MarkerBean.MB.STATE.v) + " LONG-" + hm.get(MarkerBean.MB.LONG.v) + " LAT-" +  hm.get(MarkerBean.MB.LAT.v) );
                }
            }
        }

        return res;
    }

}