package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread {
	
	int id;  
	private int cmin, cmax, tmax; 
	private Semaphore semaforo; 
	
	public ThreadController (int id, Semaphore semaforo) {
		this.id = id; 
		this.semaforo = semaforo; 
	}
	
	public void run () {
		if(id%3==1) {
			cmin = 200; 
			cmax = 1000; 
			tmax = 1000; 
			for(int i = 0; i<2; i++) {
				calculo(cmin, cmax, id); 
				try {
					semaforo.acquire();
					transação(tmax, id); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
	                semaforo.release();
				}
			}
			
		}else if(id%3==2){
			cmin = 500; 
			cmax = 1500; 
			tmax = 1500;
			for(int i = 0; i<3; i++) {
				calculo(cmin, cmax, id); 
				try {
					semaforo.acquire();
					transação(tmax, id); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
	                semaforo.release();
				}
			}
		}else if(id%3==0) {
			cmin = 1000; 
			cmax = 2000; 
			tmax = 1500;
			for(int i = 0; i<3; i++) {
				calculo(cmin, cmax, id); 
				try {
					semaforo.acquire();
					transação(tmax, id); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
	                semaforo.release();
				}
			}
		}else {
			System.out.println("Thread de id "+ id+ " não pode ser iniciada!");
		}
	}
	
	private void calculo (int cmin, int cmax, int id) {
		int range = cmax - cmin + 1;
		int tempo = (int) ((Math.random()* range) + cmin);
		System.out.println(String.format("A Thread de id "+ id + " está na fase de cálculo por "+ (double) (tempo/1000) + " segundos"));
		
		try {
			sleep(tempo);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A Thread de id "+ id + " terminou a fase de cálculo");
	}
	
	private void transação (int tmax, int id) {
		int range = tmax - 0;
		int tempo = (int) ((Math.random()* range) + 0);
		System.out.println(String.format("A Thread de id "+ id + " está na fase de transação por "+ (double) (tempo/1000) + " segundos"));
		
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A Thread de id "+ id + " terminou a fase de transação");
	}
	}
