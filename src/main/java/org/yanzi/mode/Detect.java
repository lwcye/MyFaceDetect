package org.yanzi.mode;

import java.util.List;

/**
 * 这个类的作用
 * Created by lwc on 2016/2/22.
 */
public class Detect {
    private int  img_height;
    private int  img_width;
    private String  img_id;
    private String  session_id;
    private List<face> face;

    public int getImg_height() {
        return img_height;
    }

    public void setImg_height(int img_height) {
        this.img_height = img_height;
    }

    public int getImg_width() {
        return img_width;
    }

    public void setImg_width(int img_width) {
        this.img_width = img_width;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public List<org.yanzi.mode.face> getFace() {
        return face;
    }

    public void setFace(List<org.yanzi.mode.face> face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return "Detect{" +
                "img_height=" + img_height +
                ", img_width=" + img_width +
                ", img_id='" + img_id + '\'' +
                ", session_id='" + session_id + '\'' +
                ", face=" + face +
                '}';
    }
}
