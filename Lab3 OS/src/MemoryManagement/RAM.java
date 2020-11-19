package MemoryManagement;

import java.util.ArrayList;

public class RAM {

    private ArrayList<Page> pagesArray;
    private int memorySize;
    private int pageSize;

    public RAM(int memorySize, int pageSize) {
        this.pagesArray = new ArrayList<>(memorySize / pageSize);
        this.memorySize = memorySize;
        this.pageSize = pageSize;
    }

    public boolean isPresent(int pageID) {
        for (Page page : pagesArray) {
            if (page.getID() == pageID) {
                return true;
            }
        }
        return false;
    }

    public void addPage(Page page) {
        pagesArray.add(page);
    }

    /*
    Находим страницу для выгрузки.
    Алгоритм: "Второй шанс".
     */
    public Page getPageToUnload() {
        System.out.println();
        System.out.println("Таблица страниц в физической памяти до прохода алгоритма:");
        showPages();
        for (;;) {
            /*
            Берём страницу в начале очереди (самая первая добавленная в pagesArray)
            Если у этой страницы установлен признак обращения, сбрасываем его
            и перемещаем страницу в конец очереди.
             */
            if (pagesArray.get(0).isReferred()) {
                pagesArray.get(0).setReferred(false);
                Page buf = pagesArray.get(0).copyPage();
                pagesArray.remove(0);
                pagesArray.add(buf);
                showPages();
                System.out.println();
            }
            //Иначе выгружаем эту страницу
            else {
                Page unloadPage = pagesArray.get(0).copyPage();
                pagesArray.remove(0);
                return unloadPage;
            }
        }
    }

    public void setReferred(int pageID) {
        for (Page page : pagesArray) {
            if (page.getID() == pageID) {
                page.setReferred(true);
            }
        }
    }

    public boolean hasSpace(){
        return pagesArray.size() * pageSize < memorySize;
    }

    public void showPages() {
        System.out.println("Слот памяти | № процесса | Признак обращения");
        System.out.println("\t\t\t--- Начало очереди ---\t\t\t");
        for (Page page: pagesArray) {
            System.out.printf(" %8d | \t%8d |\t %8b\n", page.getID(), page.getProcessID(), page.isReferred());
        }
        System.out.println("\t\t\t--- Конец очереди ---\t\t\t");
    }
}