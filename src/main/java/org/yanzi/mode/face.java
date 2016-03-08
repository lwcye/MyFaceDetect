package org.yanzi.mode;

/**
 * 这个类的作用
 * Created by lwc on 2016/2/22.
 */
public class face {
    private String attribute;
    private String face_id;
    private String position;
    private String tag;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getFace_id() {
        return face_id;
    }

    public void setFace_id(String face_id) {
        this.face_id = face_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "face{" +
                "attribute='" + attribute + '\'' +
                ", face_id='" + face_id + '\'' +
                ", position='" + position + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
