package homework_1;

import java.util.concurrent.CopyOnWriteArrayList;

public class PageList {
    private static CopyOnWriteArrayList<String> linkList;

    public PageList(){
        this.linkList = new CopyOnWriteArrayList<String>();
    }

    public boolean contains(String link){
        return this.linkList.contains(link);
    }

    public void add(String link){
        this.linkList.add(link);
    }
}
