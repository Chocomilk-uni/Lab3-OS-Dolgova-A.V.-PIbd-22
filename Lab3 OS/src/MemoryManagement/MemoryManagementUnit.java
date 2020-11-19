package MemoryManagement;

public class MemoryManagementUnit {
    RAM physicalMemory;
    HardDisk disk = new HardDisk();

    public MemoryManagementUnit(int memorySize, int bitForPage) {
        physicalMemory = new RAM(memorySize, bitForPage);
    }

    /*
    Метод, обрабатывающий запросы процессов на обращение к определённым страницам виртуальной памяти.
     */
    public void accessPage(int pageID) {
        if (!physicalMemory.isPresent(pageID)) {
            System.out.println("\t\t--- Страничное прерывание ---\t\t");
            reflectToPhysicalMemory(pageID);
            System.out.println("Страница виртуальной памяти " + pageID + " была отображена на свободную страницу физической памяти.");
            System.out.println("Обращение процесса к участку адресного пространства успешно завершено.");
        }
        else {
            physicalMemory.setReferred(pageID);
            System.out.println("Обращение процесса к участку адресного пространства успешно завершено.");
        }
    }

    public void addToDisk(Page page) {
        disk.addPage(page);
    }

    /*
    Метод для загрузки страниц, к к-рым обращаются процессы, в физическую память.
     */
    private void reflectToPhysicalMemory(int pageID) {
        System.out.println("Проверка наличия свободного места в оперативной памяти: " + physicalMemory.hasSpace());
        if (physicalMemory.hasSpace()) {
            physicalMemory.addPage(disk.getPage(pageID).copyPage());
            physicalMemory.setReferred(pageID);
        }
        else {
            System.out.println("Недостаточно памяти => необходимо произвести подкачку страниц.");
            paging(pageID);
            physicalMemory.setReferred(pageID);
        }
    }

    /*
    Метод, осуществляющий подкачку страниц.
     */
    private void paging(int pageID){
        Page buf = physicalMemory.getPageToUnload();
        System.out.println("Страница (ID: " + buf.getID() + ") выгружена на диск.");
        physicalMemory.addPage(disk.getPage(pageID).copyPage());
        System.out.println("Искомая страница загружена в физическую память.");
    }
}