package MemoryManagement;

import java.util.Random;

public class Memory {
    private int countElements = 0;
    private int numberInQueue = 0;

    Random random = new Random();

    private Page[] arrayPages;

    public Memory(int MemorySize, int bitForPage) {
        arrayPages = new Page[MemorySize / bitForPage];
    }

    public Page addPage(Page page, int number) {
        page.setVirtualAddress(number);
        page.setModified(random.nextBoolean());
        if (countElements == arrayPages.length) {
            sendPage();
        }
        for (int i = 0; i < arrayPages.length; i++) {
            if (arrayPages[i] == null) {
                page.setPhysicalAddress(i);
                page.setPresentOnPhysicalMemory(true);
                arrayPages[i] = page;
                System.out.println("В оперативную память добавлена страница " + number);
                countElements++;
                showSlots();
                return page;
            }
        }
        System.out.println("ОШИБКА");
        return null;
    }

    //Метод для выгрузки страницы на диск
    public Page sendPage() {
        for (int i = 0; i < arrayPages.length; i++) {
            if (numberInQueue == arrayPages.length)
                numberInQueue = 0;
            /*
            Если у страницы в начале очереди признак обращения установлен, сбрасываем его и
            перемещаем страницу в конец
             */
            if (arrayPages[numberInQueue].isModified()) {
                arrayPages[numberInQueue].setModified(false);
                numberInQueue++;
                continue;
            }
            /*
            Если признак не установлен, выгружаем страницу на диск
             */
            if (!arrayPages[numberInQueue].isModified()) {
                arrayPages[numberInQueue].setPresentOnPhysicalMemory(false);
                arrayPages[numberInQueue].setPhysicalAddress(-1);
                System.out.println("На диск выгружена страница с номером " + arrayPages[numberInQueue].getVirtualAddress()+ " процесса " + arrayPages[numberInQueue].getProcessID());
                Page page = arrayPages[numberInQueue];
                countElements--;
                arrayPages[numberInQueue] = null;
                numberInQueue++;
                return page;
            }
        }
        System.out.println("Выгрузить страницу не удалось");
        return null;
    }

    public void showSlots() {
        System.out.println("Слот памяти | № процесса | Виртуальный адрес | Признак востребованности");
        for (int i = 0; i < countElements; i++) {
            System.out.printf(" %10d | \t%8d | \t%16d |\t %10b\n",i, arrayPages[i].getProcessID(),arrayPages[i].getVirtualAddress(),arrayPages[i].isModified());
        }
    }
}