package homework_1;

import java.util.concurrent.CopyOnWriteArrayList;

public class SiteMapNode {
    private volatile CopyOnWriteArrayList<SiteMapNode> childrens;
    private String link;

    public SiteMapNode(String link){
        this.link = link;
        childrens = new CopyOnWriteArrayList<>();
    }

    public String getLink() {
        return link;
    }

    public void setChildren(SiteMapNode children){
        this.childrens.add(children);
    }

    public CopyOnWriteArrayList<SiteMapNode> getChildrens() {
        return childrens;
    }
}
