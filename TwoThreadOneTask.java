package PRACTICE;

public class TwoThreadOneTask {
public static void main(String[] args) {
	
	Canon bofors=new Canon();
	ShootingTask st=new ShootingTask(bofors);
	
	Thread Nibraz=new Thread(st,"Nibraz");
	Thread Bamsi=new Thread(st,"Bamsi");
	Nibraz.start();
	Bamsi.start();
}
}
class ShootingTask implements Runnable{
	Canon gun;
	public ShootingTask(Canon gun) {
		this.gun=gun;
	}
	@Override
	public void run() {
		Thread t=Thread.currentThread();
		if(t.getName().equals("Nibraz")) {
			for(int i=0;i<5;i++) {
				gun.fill();
				
			}
		}
		else if (t.getName().equals("Bamsi")) {
			for(int i=0;i<5;i++) {
				gun.shoot();
			}
		}
	}
}
class Canon{
	boolean flag;
	synchronized public void fill() {
		Thread t=Thread.currentThread();
		String name=t.getName();
		if(flag) {
			try {wait();}catch(Exception e) {}
		}
		System.out.println(name+"fills the gun........");
		flag=true;
		notify();
	}
	synchronized public void shoot() {
		Thread t=Thread.currentThread();
			String name=t.getName();
			if(!flag) {
				try {wait();}catch(Exception e) {}
				
			}
			System.out.println(name+"shoot the gun.........");
			flag=false;
			notify();
	}
}