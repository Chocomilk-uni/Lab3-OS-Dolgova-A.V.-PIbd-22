package MemoryManagement;

import java.util.ArrayList;
import Processing.Process;

public class MemoryManager {
    private Memory memory;
    private int countPages = 0;

    private ArrayList<Process> processArray = new ArrayList<>();

    public MemoryManager(int MemorySize, int bitForPage) {
        memory = new Memory(MemorySize, bitForPage);
    }
    public void addProcess(int processID, int pageCount) {
        Process process = new Process(processID, pageCount);
        for (int i = 0; i <= pageCount ; i++) {
            process.addPage(new Page(processID,countPages));
        }
        processArray.add(process);
        System.out.println("Процесс " + processID + " создан. Для него требуется " + pageCount + " страниц памяти.");
    }

    public  void work() {
        while (!processArray.isEmpty()) {
            for (int i = 0; i < processArray.size(); i++) {
                if (processArray.get(i).getCountPages() >= 1) {
                    memory.addPage(processArray.get(i).getPage(),countPages += 1);
                }
                else {
                    System.out.println("Процесс " + processArray.get(i).getID() + " успешно завершён.");
                    processArray.remove(i);
                    i--;
                }
            }
        }
    }
}