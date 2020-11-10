package MemoryManagement;

public class Page {

    private int processID;
    private int physicalAddress;
    private int virtualAddress;

    //Признак обращения
    private boolean isModified;
    //Признак наличия в физической памяти
    private boolean isPresentOnPhysicalMemory;

    public Page(int processID,int virtualAddress) {
        this.processID = processID;
        this.physicalAddress = -1;
        this.virtualAddress = virtualAddress;
        this.isModified = false;
        this.isPresentOnPhysicalMemory = false;
    }

    public boolean isModified() {
        return isModified;
    }

    public boolean isPresentOnPhysicalMemory() {
        return isPresentOnPhysicalMemory;
    }

    public void setPresentOnPhysicalMemory(boolean presentOnPhysicalMemory) {
        this.isPresentOnPhysicalMemory = presentOnPhysicalMemory;
    }

    public void setModified(boolean modified) {
        this.isModified = modified;
    }

    public void setPhysicalAddress(int physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public int getVirtualAddress() {
        return virtualAddress;
    }

    public void setVirtualAddress(int virtualAddress) {
        this.virtualAddress = virtualAddress;
    }

    public int getProcessID() {
        return processID;
    }
}