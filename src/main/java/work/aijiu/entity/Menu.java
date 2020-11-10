package work.aijiu.entity;


import com.baomidou.mybatisplus.annotation.TableField;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public String id;
    public String title;
    public String parentId;
    public String icon;
    public String index;
    @TableField(exist = false)
    public List<Menu> subs;

    public Menu(String id, String title, String parentId, String icon, String index) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.icon = icon;
        this.index = index;
    }
    public void addSubs(Menu menu){
        if(subs == null) {
            subs = new ArrayList<>();
        }
        subs.add(menu);
    }


    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", parentId='" + parentId + '\'' +
                ", icon='" + icon + '\'' +
                ", index='" + index + '\'' +
                ", subs=" + subs +
                '}';
    }

    public String getParentId() {
        return parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getSubs() {
        return subs;
    }

    public void setSubs(List<Menu> subs) {
        this.subs = subs;
    }
}


