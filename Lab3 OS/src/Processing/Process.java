package Processing;

import MemoryManagement.Page;

import java.util.ArrayList;

public class Process {
    private int ID;
    private int pagesCount;
    private ArrayList<Page> processesArray;
    private ArrayList<Integer> pagesIDs;

    public Process(int id, int pagesCount) {
        this.ID = id;
        this.pagesCount = pagesCount;
        this.processesArray = new ArrayList<>();
        this.pagesIDs = new ArrayList<>();
    }

    public void addPage(Page page)
    {
        processesArray.add(page);
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Integer> getPagesIDs() {
        return pagesIDs;
    }

    public Page getPage() {
        Page page = processesArray.get(processesArray.size() - 1);
        processesArray.remove(processesArray.size() - 1);
        pagesCount--;
        return page;
    }

    public int getCountPages() {
        return pagesCount;
    }
}