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
	private int asetasuna;
    private static Partida nirePartida;
	private int Puntuazioa;

	private Partida(){
        this.score = 0;
        //this.izena = "";
        this.candy = 0;
        this.soup = 0;
		this.bizitza = 20;
		this.asetasuna = 40;
        this.tamagotchi = new Egg(false, false);
    }

	public void resetPartida(){
		nirePartida = null;
	}

	public static Partida getPartida()
    {
        if(nirePartida == null)
        {
			System.out.println("partida berria egin da");
            nirePartida = new Partida();
        }
        return nirePartida;
    }
    public static void main(String [] args){
		Partida.getPartida().partidaBuklea();
	}

	public void partidaBuklea(){
			boolean bukatuta = false;

			Partida nirePartida =new Partida();
			panelNagusia frame = new panelNagusia(nirePartida);
			nirePartida.hasieratuPartida();
			try{
				Thread.sleep(4000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		while(!amaituDa()){		
			nirePartida.minijokoaJokatu();
			nirePartida.bihotzakEguneratu();
			nirePartida.sopakEguneratu(); 
			nirePartida.scoreEguneratu();
			if(nirePartida.getGaixorik()==false && nirePartida.getKaka()==false) {
					nirePartida.kakaEgin();
			}
			try{
				Thread.sleep(4000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			nirePartida.eboluzionatuTamagotchi();
		}
	}

    public void partidaBatJokatu(){
			boolean bukatuta = false;

			Partida nirePartida =new Partida();
			panelNagusia frame = new panelNagusia(nirePartida);
			nirePartida.hasieratuPartida();
			//frame.setVisible(true);
			try{
				Thread.sleep(4000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			nirePartida.eboluzionatuTamagotchi();
			nirePartida.tamagotchiEguneratu();
			Thread lausegundo = new Thread(()->{
				if(!amaituDa()){
					while(!bukatuta){
						System.out.println("sartu da lausegundoan");
						nirePartida.bihotzakEguneratu();
						nirePartida.sopakEguneratu(); 
						nirePartida.scoreEguneratu();
						nirePartida.minijokoaJokatu();
						
						//nirePartida.tamagotchiEguneratu();
						try{
							Thread.sleep(20000);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
	
					}
				}
			});
			lausegundo.start();
			Thread hilo20segundo= new Thread(()->{
				if(!amaituDa()){
				while (nirePartida.getGaixorik()==false && nirePartida.getKaka()==false) {
					nirePartida.kakaEgin();
					try {
						Thread.sleep(20000); // Espera 20 segundo
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			});
			hilo20segundo.start();

			try {
				Thread.sleep(20000); // Espera 20 segundo
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(!amaituDa()){
				nirePartida.eboluzionatuTamagotchi();
			}
			System.out.println("40 falta");
			
			try{
				Thread.sleep(10000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("azkenboluzioa");
			if(!amaituDa()){
				nirePartida.eboluzionatuTamagotchi();
			}
			lausegundo.interrupt();
			/*
			nirePartida.tamagotchiEguneratu();
			
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
		return tamagotchi.getTamagotchi().gaixorik;
	}
	private boolean getKaka(){
		return tamagotchi.getTamagotchi().kaka;
	}
	public void tamaSendatu(){
		//this.tamagotchi.gaixorik=false;
		tamagotchi.getTamagotchi().setGaixorik(false);
		System.out.println("gaixo garbitu dut");
		System.out.println(tamagotchi.getTamagotchi().gaixorik);
	}
	public void tamaKakaGarbitu(){
		//this.tamagotchi.kaka=false;
		tamagotchi.getTamagotchi().setKaka(false);
		System.out.println("kaka garbitu dut");
		System.out.println(tamagotchi.getTamagotchi().kaka);
	}
	private void hasieratuPartida(){
		setChanged();
		notifyObservers("hasieratu");
	}
	public int tamaJan(int biz, int ase){
		int punt=0;
		System.out.println(biz);
		System.out.println(ase);
		//this.tamagotchi.bizitza = this.tamagotchi.bizitza + biz;
		if(biz!=0){
			punt=biz * 3;
			//this.tamagotchi.bihotzakEguneratu(biz);
			this.bizitza = this.bizitza +(10 * biz);
			System.out.println(bizitza+" bizitzan sartu nahiz");
			bihotzakEguneratu();
		}
		if (ase!=0) {
			punt= ase * 3;
			//this.tamagotchi.katiluakEguneratu(ase);
			this.asetasuna = this.asetasuna +(10 * ase);
			sopakEguneratu();
		}
		if(biz!=0 && ase!=0){
			punt=(ase + biz)*(ase *3 + biz *3);
		}
		//this.tamagotchi.asetasuna = this.tamagotchi.asetasuna + ase;
		/*System.out.println("tama bizitza con getter"+tamagotchi.getTamagotchi().getBizitzaTama());
		System.out.println("tama bizitza sin geter---------------/"+this.tamagotchi.getBizitzaTama());
		System.out.println("tamagotchi asetasuna(tamajan)-----------/"+this.tamagotchi.getAseTama());
		System.out.println("asetasuna tama con geter -----------------/ "+ tamagotchi.getTamagotchi().getAseTama());*/
		return punt;
	}

    private void bihotzakEguneratu() {
		//this.tamagotchi.kontadoreakEguneratu();
		//int zenb1 = this.tamagotchi.getBizitzaTama();
		//int kont=0;
		System.out.println(bizitza+" de bihotzak eguneratu");
		if(this.bizitza > 40){
			diferencia = this.bizitza-40;
			this.bizitza= this.bizitza - diferencia - 5 ;
			System.out.println("ha entrado en la diferencia con vida"+this.bizitza + "diferencia"+diferencia);
		}
		
		if (this.bizitza>=31) {
			System.out.println("4 bihotz jarriko ditut");
			setChanged();
            notifyObservers("4bihotzjarri");
		}
		if (this.bizitza>=21 && this.bizitza<=30) {
			setChanged();
            notifyObservers("3bihotzjarri");
		}
		if (this.bizitza>=11 && this.bizitza<=20) {
			setChanged();
            notifyObservers("2bihotzjarri");
		}
		if (this.bizitza>=1 && this.bizitza<=10) {
			setChanged();
            notifyObservers("bihotz1jarri");
		}
        if (this.bizitza<=0){
			setChanged();
            notifyObservers("hildaDagoBihotz");
		}
	}
    private void sopakEguneratu() {
		//tamagotchi.getTamagotchi().kontadoreakEguneratu();
		
		System.out.println("asetasun(zenb de sopakeguneratu)------------/"+ this.asetasuna);
		//int kont=0;
		if (this.asetasuna>41){
			diferencia = this.asetasuna-40;
			
			this.asetasuna = this.asetasuna - diferencia - 5 ;

		}
		if (this.asetasuna>=31) {
			setChanged();
            notifyObservers("4sopajarri");
		}
		if (this.asetasuna>=21 && this.asetasuna<=30) {
			setChanged();
            notifyObservers("3sopajarri");
		}
		if (this.asetasuna>=11 && this.asetasuna<=20) {
			setChanged();
            notifyObservers("2sopajarri");
		}
		if (this.asetasuna>=1 && this.asetasuna<=10) {
			setChanged();
            notifyObservers("sopa1jarri");
		}
		if(this.asetasuna<=0){
			setChanged();
            notifyObservers("hildaDagoKat");
		}
	}
	private void kakaEgin(){ //mira si se hace kk
		Random probabilitatea = new Random();
		System.out.println("KAIXO");
		int zenbakia = probabilitatea.nextInt(101);
		System.out.println("kaka zenb "+zenbakia);
		if(1<=zenbakia && zenbakia<=20 && this.tamagotchi.kaka==false){
			this.tamagotchi.getTamagotchi().setKaka(true);
			kakaBistaratu(true);
		}
		else{
			System.out.println("kaka ez du egin");
			this.tamagotchi.getTamagotchi().setKaka(false);
			gaixotuAhalDa(this.tamagotchi.kaka); //aqui salta a otro metodo para ver si se puede enfermar (no puede hacer kaka y enfermar a la misma vez)
		}
	}
	private void gaixotuAhalDa(boolean kaka){
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
	private boolean minijokoaJokatu(){
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
		System.out.println(izena);
		if (izena == "Marutchi") {
			setChanged();
			notifyObservers("marutchi");
		}
		if (izena == "Kuchipatchi") {
			setChanged();
			notifyObservers("kuchipatchi");
		}
		if (izena == "Maskutchi") {
			setChanged();
			notifyObservers("maskutchi");
		}
		if (izena == "Mimitchi") {
			System.out.println("hemen nago mimi");
			setChanged();
			notifyObservers("mimitchi");
		}

	}
	private void eboluzionatuTamagotchi(){
		if(tamagotchi instanceof Egg || tamagotchi instanceof Kuchipatchi || tamagotchi instanceof Mimitchi){
			tamagotchi = tamagotchi.eboluzionatuTama();
			tamagotchiEguneratu();
		}
	}
	/*private void eboluzionatuTamagotchi2(){//esto hay que ponerlo bien solamente era una prueba ra ver si funcionaban las co
		//this.tamagotchi = new Mimitchi(40, 40, false, false);
		//tamagotchiEguneratu();
		System.out.println("mimimi");
		//this.tamagotchi = this.tamagotchi.getTamagotchi().eboluzionatuTama2(this.tamagotchi.getTamagotchi().getKaka(), this.tamagotchi.getTamagotchi().getGaixorik());
		tamagotchi= tamagotchi.getTamagotchi();
		this.tamagotchi= tamagotchi.eboluzionatuTama2(tamagotchi.kaka,tamagotchi.gaixorik);
		System.out.println(tamagotchi.getTamagotchi().kaka);
		tamagotchiEguneratu();
	}*/
	public int getScore(){
		//Puntuazioa = this.score;
		return scoreEguneratu();
	}
	private int Puntuacion;
	private int diferencia;
	private int scoreEguneratu(){
		//+1 cada vez que pasan 4 segundos
		Puntuacion=Puntuacion +1;
		if (this.tamagotchi.gaixorik==true){
			Puntuacion =Puntuacion - 5;
		}
		if (this.tamagotchi.kaka==true){
			Puntuacion =Puntuacion - 5;
		}
		if (this.tamagotchi.zeinEboluzioDa().equals("Marutchi")){
			Puntuacion =Puntuacion + 20;
		}
		if(bizitza>40){
			Puntuacion = Puntuacion -5;
		}
		if(asetasuna>40){
			Puntuacion = Puntuacion -5;
		}
		/*Minijokoa minijokoa=Minijokoa.getMinijokoa();
		if(minijokoa.irabaziDu()==true){
			Puntuacion =Puntuacion + 20;
		}
		if(minijokoa.irabaziDu()==false){
			Puntuacion =Puntuacion - 20;
		}*/

		//si se da una piruleta o sopa +5 
		setChanged();
		notifyObservers("Puntuazioa");
		return Puntuacion;
	}
	public boolean amaituDa( ){
		//int zenb = tamagotchi.getTamagotchi().getBizitza();
		//int zenb1= tamagotchi.getTamagotchi().getAsetasuna();
		//int zenb=tamagotchi.getTamagotchi().getBizitza();
		int b = bizitza;
		int a = asetasuna;
		boolean amaitu =false;
		if (b<=0){
			amaitu=true;
			notifyObservers("hildaDagoBihotz");
			setChanged();
		}
		if (a<=0){
			amaitu=true;
			notifyObservers("hildaDagoKat");
			setChanged();
		}
		return amaitu;
	}
	public void sumarPuntuacion(int puntuacion){
		Puntuacion = Puntuacion + puntuacion;
	}

	/*private void azkeneboluzioa(){
		tamagotchi = tamagotchi.getTamagotchi();
		this.tamagotchi=tamagotchi.azkenEbol(tamagotchi.kaka, tamagotchi.gaixorik);
		tamagotchiEguneratu();
	}*/
}
