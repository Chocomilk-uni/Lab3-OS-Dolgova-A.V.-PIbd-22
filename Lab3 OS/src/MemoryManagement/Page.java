package MemoryManagement;

public class Page {

    private final int processID;
    private final int ID;
    //Признак обращения
    private boolean isReferred = false;

    public Page(int id, int processID) {
        this.ID = id;
        this.processID = processID;
    }

    public int getID() {
        return ID;
    }

    public boolean isReferred() {
        return isReferred;
    }

    public void setReferred(boolean referred) {
        isReferred = referred;
    }

    public Page copyPage() {
        return new Page(this.getID(), this.getProcessID());
    }

    public int getProcessID() {
        return processID;
    }
}