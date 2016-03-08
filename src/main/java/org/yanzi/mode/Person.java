package org.yanzi.mode;

/**
 * 这个类的作用
 * Created by lwc on 2016/2/22.
 */
public class Person {
    private String person_id;
    private String person_name;
    private String tag;

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id='" + person_id + '\'' +
                ", person_name='" + person_name + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
