package org.yanzi.mode;

import java.util.List;

/**
 * 这个类的作用
 * Created by lwc on 2016/2/22.
 */
public class Group {
    private int added_person;
    private String group_id;
    private String group_name;
    private String tag;
    private List<Person> person;

    public int getAdded_person() {
        return added_person;
    }

    public void setAdded_person(int added_person) {
        this.added_person = added_person;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Group{" +
                "added_person=" + added_person +
                ", group_id='" + group_id + '\'' +
                ", group_name='" + group_name + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
