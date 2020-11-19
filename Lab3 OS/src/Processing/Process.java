package Processing;

import MemoryManagement.MemoryManagementUnit;
import MemoryManagement.Page;

import java.util.ArrayList;

public class Process {
    private int ID;
    private int pagesCount;
    ArrayList<Page> pagesArray = new ArrayList<>();

    public Process(int id, int pagesCount) {
        this.ID = id;
        this.pagesCount = pagesCount;
    }

    /*
    Метод для "инициализации" страницы в процессе
     */
    public void addPage(Page page) {
        pagesArray.add(page);
    }

    public int getID() {
        return ID;
    }

    public int getNumberOfPages() {
        return pagesCount;
    }

    public void work(MemoryManagementUnit memoryManager) {
        /*
        Процесс обращается к случайной странице виртуальной памяти
         */
        int startID = pagesArray.get(0).getID();
        int ID = startID + (int) (Math.random() * getNumberOfPages());
        System.out.println("Процесс " + getID() + " обращается к странице виртуальной памяти с ID " + ID + ".");
        memoryManager.accessPage(ID);
        System.out.println();
    }
}