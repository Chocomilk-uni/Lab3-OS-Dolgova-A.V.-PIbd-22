package Processing;

import MemoryManagement.MemoryManager;

public class  Main {
    public static void main (String[] args) {
        MemoryManager memoryManager = new MemoryManager(16,4);
        for (int i = 0; i < 3 + (int) (Math.random() * 5); i++) {
            memoryManager.addProcess(i, 2 + (int) (Math.random() * 5));
        }
        memoryManager.work();
    }
}