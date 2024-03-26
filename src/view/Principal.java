package view;

import java.util.concurrent.Semaphore;

import controller.ThreadController;

public class Principal {
	
	public static void main(String[] args) {
		
		int permissoes = 1; 
		Semaphore semaforo = new Semaphore (permissoes); 
		
		for(int id=1; id<22; id++ ) {
			Thread ttransações = new ThreadController(id, semaforo); 
			ttransações.start(); 
		}
		
	}

}
