package com.smartbot.integration.util;

import java.util.HashMap;

public interface Constants {

  HashMap<String, String> fbPageMapper = new HashMap<String, String>() {
    private static final long serialVersionUID = -1644315397076096817L;
    {
      put("pageid1", "pageAccessToken1");
      put("pageid2", "pageAccessToken2");
    }
  };

}
