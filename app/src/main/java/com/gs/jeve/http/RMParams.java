package com.gs.jeve.http;

import java.util.HashMap;
import java.util.Map;

/*********************************************
 ***                                       ***
 ***                                       ***
 ***       Created by HC on 2017/5/10.       ***
 *********************************************/

public class RMParams {

    private Map<String, String> params;

    public RMParams() {
        params = new HashMap<String, String>();
    }

    public Map<String, String> login(String usercode, String password) {
        params.put("user.usercode", usercode);
        params.put("user.password", password);
        return params;
    }

    public Map<String, String> upload(String upath) {
        params.put("file", upath);
        return params;
    }
}
