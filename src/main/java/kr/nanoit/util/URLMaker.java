package kr.nanoit.util;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLMaker {

    private String totalUrl;

    public URLMaker() {
    }


    /**
    * @param url request url
     * @param id request id
     * @param pw request password
     * @return
    */
    public String makeUrlParam(String url,String id, String pw) {
        try {
            String data = "id=" + id + "&" + "password=" + URLEncoder.encode(pw, StandardCharsets.UTF_8.toString());
            if(data != null && data.length() > 0 && !data.equals("") && !data.contains("null")) {
                totalUrl =  url.trim() + "?" + data.trim();
            }
            else {
                totalUrl = url.trim();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalUrl;
    }
}
