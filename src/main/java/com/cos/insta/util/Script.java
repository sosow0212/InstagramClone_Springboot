package com.cos.insta.util;

public class Script {

    public static String back(String msg) {
        // 경고창 하나 띄우고 뒤로 돌아감
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+msg+"');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }
}
