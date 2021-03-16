package controller;

import java.util.concurrent.Semaphore;

public class PratoThread extends Thread{
	
	private int id;
	private Semaphore semaforo;
	private int tempoPreparo;
	
	public PratoThread(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		cozinhar();
		try {
			semaforo.acquire();
			entregar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void cozinhar() {
		if(id%2==0) {
			tempoPreparo = (int) ((Math.random() * 601) + 600);
			int porcentual = tempoPreparo / 100;
			int total=0;
			System.out.println("Lasanha a Bolonhesa #"+id+" iniciou preparo!");
			do {
				try {
					sleep(100);
					if(total+porcentual>=100) {
						total = 100;
					}else {
						total+=porcentual;
					}
					System.out.println("Lasanha a Bolonhesa #"+id+" em "+total+"% concluida");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}while(total <100);
		}else {
			tempoPreparo = (int) ((Math.random() * 301) + 500);
			int porcentual = tempoPreparo / 100;
			int total=0;
			System.out.println("Sopa de Cebola #"+id+" iniciou preparo!");
			do {
				try {
					sleep(100);
					if(total+porcentual>100) {
						total = 100;
					}else {
						total+=porcentual;
					}
					System.out.println("Sopa de Cebola #"+id+" em "+total+"% concluida");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}while(total <100);
		}
	}

	private void entregar() {
		if(id%2==0) {
			System.out.println("Entregando a Lasanha a Bolonhesa #"+id+"...");
		}else {
			System.out.println("Entregando a Sopa de Cebola #"+id+"...");
		}
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
