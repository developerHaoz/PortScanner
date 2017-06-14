package com.example.developerhaoz.portscanner.common;

import com.android.volley.VolleyError;

/**
 * Created by developerHaoz on 2017/6/14.
 */

public interface VolleyResponseCallback {
    void onSuccess(String response);
    void onError(VolleyError error);
}
