package Processing;

import MemoryManagement.MemoryManagementUnit;
import MemoryManagement.Page;

import java.util.ArrayList;

public class Core {
    final int NUMBER_OF_PROCESSES = 10;
    final int NUMBER_OF_WORK_ITERATIONS = 30;
    final int MEMORY_SIZE = 32;
    final int PAGE_SIZE = 4;
    /*
    Ядро содержит таблицу процессов с целью их отслеживания.
     */
    ArrayList<Process> processArray = new ArrayList<>();
    MemoryManagementUnit memoryManager = new MemoryManagementUnit(MEMORY_SIZE, PAGE_SIZE);
    private int processCount = 0;
    private int pageCount = 0;

    /*
    Запуск работы процессов.
     */
    public void start() {
        for (int i = 0; i < NUMBER_OF_WORK_ITERATIONS; i++) {
            for (Process process : processArray) {
                process.work(memoryManager);
            }
        }
    }

    /*
    Метод, создающий процессы и случайным образом устанавливающий кол-во страниц их адресного пространства.
    Изначально они располагаются на диске.
     */
    public void createProcesses() {
        for (int i = 0; i < NUMBER_OF_PROCESSES; i++) {
            Process process = new Process(processCount, (int) (1 + Math.random() * 4));
            processArray.add(process);
            System.out.println("Процесс (ID: " + process.getID() + ") создан.\nЕго адресное пространство занимает: " + process.getNumberOfPages() + " стр.");
            processCount++;
            for (int j = 0; j < process.getNumberOfPages(); j++) {
                Page page = new Page(pageCount, process.getNumberOfPages());
                System.out.println("Страница (ID: " + pageCount + ")");
                pageCount++;
                process.addPage(page);
                memoryManager.addToDisk(page.copyPage());
            }
            System.out.println("\t\t\t______________________\t\t\t");
            System.out.println();
        }
    }
}
