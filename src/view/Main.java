package view;

import java.util.concurrent.Semaphore;

import controller.PratoThread;

public class Main {
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		for (int i = 0; i < 5; i++) {
			PratoThread pt = new PratoThread(i, semaforo);
			pt.start();
		}
		
	}
}
