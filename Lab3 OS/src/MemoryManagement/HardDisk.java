package MemoryManagement;

import java.util.ArrayList;

public class HardDisk {
    private ArrayList<Page> pagesArray = new ArrayList<>();

    public void addPage(Page page) {
        pagesArray.add(page);
    }

    public Page getPage(int pageID) {
        for (Page page: pagesArray) {
            if (page.getID() == pageID)
                return page;
        }
        return null;
    }
}