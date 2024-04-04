package src.Eredua;

import java.util.*;

import src.Bista.panelNagusia;

import java.io.*;

public class Partida extends Observable{
    private int score;
    private String izena;
    private int candy;
    private int soup;
    private Tamagotchi tamagotchi;
	private int bizitza;
    private static Partida nirePartida;
	public Partida(){
        this.score = 0;
        //this.izena = "";
        this.candy = 0;
        this.soup = 0;
        this.tamagotchi = new Kuchipatchi(15, 15, false, false);
    }

	public void resetPartida(){
		nirePartida = null;
	}

	public static Partida getPartida()
    {
        if(nirePartida == null)
        {
            nirePartida = new Partida();
        }
        return nirePartida;
    }
    public static void main(String [] args){
			Partida nirePartida =new Partida();
			panelNagusia frame = new panelNagusia(nirePartida);

			nirePartida.hasieratuPartida();
			try{
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			nirePartida.tamagotchiEguneratu();
			Thread lausegundo = new Thread(()->{
					while(true){
						System.out.println("lau segundo pasa dira");
						nirePartida.bihotzakEguneratu();
						nirePartida.sopakEguneratu(); 
						nirePartida.scoreEguneratu();
						//nirePartida.tamagotchiEguneratu();
						try{
							Thread.sleep(4000);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
	
					}
				});
			lausegundo.start();
			Thread hilo20segundo= new Thread(()->{
				while (nirePartida.getGaixorik()==false && nirePartida.getKaka()==false) {
					nirePartida.kakaEgin();
					try {
						Thread.sleep(10000); // Espera 20 segundo
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			hilo20segundo.start();
			Thread hilo15segundo =new Thread(()->{
				while (true) {
					nirePartida.eboluzionatuTamagotchi();
					try{
						Thread.sleep(15000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			hilo15segundo.start();
			//nirePartida.tamagotchiEguneratu();
			/* 
			Thread lausegundo = new Thread(()->{
				while(true){
					nirePartida.bihotzakEguneratu();
					nirePartida.sopakEguneratu(); 
					nirePartida.scoreEguneratu();
					nirePartida.tamagotchiEguneratu();
					try{
						Thread.sleep(4000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			});
			lausegundo.start();
			Thread hilo20segundo= new Thread(()->{
				while (nirePartida.kakaEgin()==false && nirePartida.gaixorikEgon()==false) {
					nirePartida.kakaEgin();
					try {
						Thread.sleep(10000); // Espera 20 segundo
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			hilo20segundo.start();
			Thread hilo15segundo =new Thread(()->{
				while (true) {
					nirePartida.eboluzionatuTamagotchi();
					try{
						Thread.sleep(15000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			hilo15segundo.start();
			try{
				Thread.sleep(40000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			nirePartida.azkeneboluzioa();*/
    }
	private boolean getGaixorik(){
		return this.tamagotchi.gaixorik;
	}
	private boolean getKaka(){
		return this.tamagotchi.kaka;
	}
	private void hasieratuPartida(){
		setChanged();
		notifyObservers("hasieratu");
	}
    private void bihotzakEguneratu() {
		int zenb = this.tamagotchi.bizitza;
		//int kont=0;
		if (zenb>=31 && zenb<=40) {
			setChanged();
            notifyObservers("4bihotzjarri");
		}
		if (zenb>=21 && zenb<=30) {
			setChanged();
            notifyObservers("3bihotzjarri");
		}
		if (zenb>=11 && zenb<=20) {
			setChanged();
            notifyObservers("2bihotzjarri");
		}
		if (zenb>=1 && zenb<=10) {
			setChanged();
            notifyObservers("bihotz1jarri");
		}
        else{
			setChanged();
            notifyObservers("hildaDago");
		}
	}
    private void sopakEguneratu( ) {
		int zenb = this.tamagotchi.asetasuna;
		//int kont=0;
		if (zenb>=31 && zenb<=40) {
			setChanged();
            notifyObservers("4sopajarri");
		}
		if (zenb>=21 && zenb<=30) {
			setChanged();
            notifyObservers("3sopajarri");
		}
		if (zenb>=11 && zenb<=20) {
			setChanged();
            notifyObservers("2sopajarri");
		}
		if (zenb>=1 && zenb<=10) {
			setChanged();
            notifyObservers("sopa1jarri");
		}
		else{
			setChanged();
            notifyObservers("hildaDago");
		}
	}
	private void kakaEgin(){ //mira si se hace kk
		Random probabilitatea = new Random();
		System.out.println("KAIXO");
		int zenbakia = probabilitatea.nextInt(101);
		System.out.println("kaka zenb "+zenbakia);
		if(1<=zenbakia && zenbakia<=20){
			this.tamagotchi.kaka =true;
			kakaBistaratu(this.tamagotchi.kaka); //aqui si se hace kk salta
		}
		else{
			System.out.println("kaka ez du egin");
			this.tamagotchi.kaka=false;
			gaixotuAhalDa(this.tamagotchi.kaka); //aqui salta a otro metodo para ver si se puede enfermar (no puede hacer kaka y enfermar a la misma vez)
		}
	}
	private void gaixotuAhalDa(boolean kaka){
		System.out.println(kaka);
		if (kaka==false) {
			this.tamagotchi.kaka=false;
			gaixorikEgon();
		}
	}
	private void gaixorikEgon(){
		Random probabilitatea = new Random();
		int zenbakia = probabilitatea.nextInt(101);
		System.out.println("gaixo zenb "+zenbakia);
		if(1<=zenbakia && zenbakia<=30){
			this.tamagotchi.gaixorik = true;
			gaixoBistaratu(this.tamagotchi.gaixorik );
		}
	}
	private boolean minijokoaJokaatu(){
		Random probabilitatea = new Random();
		int zenbakia = probabilitatea.nextInt(101);
		if(1<=zenbakia && zenbakia<=12){
			setChanged();
			notifyObservers("MiniJokua");
			return true;
		}
		else{
			return false;
		}
	}
	private void kakaBistaratu(boolean kaka){
		if(kaka==true){
			setChanged();
			notifyObservers("kaka");
		}
	}
	private  void gaixoBistaratu(boolean gaixo){
		if(gaixo==true){
			setChanged();
			notifyObservers("gaixo");
		}
	}
	private void tamagotchiEguneratu( ){
		String izena =this.tamagotchi.zeinEboluzioDa();
		System.out.println(izena);;
		if (izena == "Marutchi") {
			setChanged();
			notifyObservers("Marutchi");
		}
		if (izena == "Kuchipatchi") {
			setChanged();
			notifyObservers("Kuchipatchi");
		}
		if (izena == "Maskutchi") {
			setChanged();
			notifyObservers("Maskutchi");
		}
		if (izena == "Mimitchi") {
			setChanged();
			notifyObservers("Mimitchi");
		}

	}
	private void eboluzionatuTamagotchi(){//esto hay que ponerlo bien solamente era una prueba ra ver si funcionaban las co
				//this.tamagotchi = new Mimitchi(40, 40, false, false);
				//tamagotchiEguneratu();
				System.out.println("mimimi");
				nirePartida.tamagotchi = nirePartida.tamagotchi.eboluzionatuTama();
				nirePartida.tamagotchiEguneratu();
	}
	public int getScore(){
		return nirePartida.score;
	}
	private void scoreEguneratu(){
		int puntuazioa=nirePartida.score;
		//+1 cada vez que pasan 4 segundos
		if (this.tamagotchi.gaixorik==true){
			puntuazioa=puntuazioa-5;
		}
		if (this.tamagotchi.kaka==true){
			puntuazioa=puntuazioa-5;
		}
		if (this.tamagotchi.zeinEboluzioDa()=="Marutchi"){
			puntuazioa=puntuazioa+20;
		}
		//si se da una piruleta o sopa +5 
		setChanged();
		notifyObservers("Puntuazioa");
	}
	private void azkeneboluzioa(){
		this.tamagotchi=this.tamagotchi.azkenEbol(this.tamagotchi.kaka, this.tamagotchi.gaixorik);
		nirePartida.tamagotchiEguneratu();
	}
}
