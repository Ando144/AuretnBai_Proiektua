package src.Eredua;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import src.Bista.miniJokoaBista;


public class Minijokoa extends Observable{
   
   private static int[][] laukiak = new int [12][12]; 
   private static boolean[][] tarta = new boolean [12][12];
   private static boolean[][] tamagochi = new boolean [12][12];
   private static int tamagochiLerroa;
   private static int tamagochiZutabea;
   private static int tartaLerroa;
   private static int tartaZutabea;
    
    private static Minijokoa NireMinijokoa = null;
    public static Minijokoa getMinijokoa()
    {
        if(NireMinijokoa == null)
        {
            NireMinijokoa = new Minijokoa();
        }
        return NireMinijokoa;
    }
    public static void main(String[] args) 
    {
        Minijokoa minijokoa = new Minijokoa();
        miniJokoaBista bista = new miniJokoaBista(minijokoa);
        System.out.println("1");
        minijokoa.hasieratuLaukiak();
        minijokoa.TamagochietaTartaHasieratu();
        System.out.println("5");
    }
    private void hasieratuLaukiak()
    {
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 12; j++)
            {
                laukiak[i][j] = (int) (Math.random() * 3) + 1;//mirar esto pq el random no debe ser 0 tmb
            }
        }
        setChanged();
        System.out.println("2");
        notifyObservers("hasieratu");
    }
    public int getLerroa(){
        return tamagochiLerroa;
    }
    public int getZutabea(){
        return tamagochiZutabea;
    }
    public static int getTartaLerroa()
    {
        return tartaLerroa;
    }
    public static int getTartaZutabea()
    {
        return tartaZutabea;
    }
    
    public int[][] getLaukiak()
    {
        return laukiak;
    }
    public boolean tartaVisible()
    {
        if(laukiak[tartaLerroa][tartaZutabea] == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean tamagochiVisible()
    {
        if(laukiak[tamagochiLerroa][tamagochiZutabea] == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private static boolean mugimenduaEginDaiteke(int norabidea)
    /*comprueba si el movimiento esta dentro de la matriz 
    y si la casilla a la que se quiere desplazar esta con laukiak de valor 0*/
    {
        if(tamagochiVisible()){
            if (norabidea == 1)
            {
                if (tamagochiLerroa > 0 && laukiak[tamagochiLerroa - 1][tamagochiZutabea] != 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (norabidea == 2)
            {
                if (tamagochiLerroa < 11 && laukiak[tamagochiLerroa + 1][tamagochiZutabea] != 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (norabidea == 3)
            {
                if (tamagochiZutabea > 0 && laukiak[tamagochiLerroa][tamagochiZutabea - 1] != 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (norabidea == 4)
            {
                if (tamagochiZutabea < 11 && laukiak[tamagochiLerroa][tamagochiZutabea + 1] != 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    public void TamagochiMugitu(int norabidea)
    //mueve el tamagochi en la matriz siempre y cuando sea movimiento valido
    {
        
        if (norabidea == 1)
        {
            if (mugimenduaEginDaiteke(1))
            {
                tamagochi[tamagochiLerroa][tamagochiZutabea] = false;
                tamagochiLerroa++;
                tamagochi[tamagochiLerroa][tamagochiZutabea] = true;
                if(irabaziDu())
                {
                    setChanged();
                    notifyObservers("irabaziDu");
                }
                else
                {
                    setChanged();
                    notifyObservers("tamagochiMugitu");
                }
            }
        }
        else if (norabidea == 2)
        {
            if (mugimenduaEginDaiteke(2))
            {
                tamagochi[tamagochiLerroa][tamagochiZutabea] = false;
                tamagochiLerroa--;
                tamagochi[tamagochiLerroa][tamagochiZutabea] = true;
                if(irabaziDu())
                {
                    setChanged();
                    notifyObservers("irabaziDu");
                }
                else
                {
                    setChanged();
                    notifyObservers("tamagochiMugitu");
                }
            }
        }
        else if (norabidea == 3)
        {
            if (mugimenduaEginDaiteke(3))
            {
                tamagochi[tamagochiLerroa][tamagochiZutabea] = false;
                tamagochiZutabea--;
                tamagochi[tamagochiLerroa][tamagochiZutabea] = true;
                if(irabaziDu())
                {
                    setChanged();
                    notifyObservers("irabaziDu");
                }
                else
                {
                    setChanged();
                    notifyObservers("tamagochiMugitu");
                }

            }
        }
        else if (norabidea == 4)
        {
            if (mugimenduaEginDaiteke(4))
            {
                tamagochi[tamagochiLerroa][tamagochiZutabea] = false;
                tamagochiZutabea++;
                tamagochi[tamagochiLerroa][tamagochiZutabea] = true;
                if(irabaziDu())
                {
                    setChanged();
                    notifyObservers("irabaziDu");
                }
                else
                {
                    setChanged();
                    notifyObservers("tamagochiMugitu");
                }
            }
        }
    }
    private  void TamagochietaTartaHasieratu()
    {
        tamagochiLerroa = (int) (Math.random() * 12);
        tamagochiZutabea = (int) (Math.random() * 12);
        tamagochi[tamagochiLerroa][tamagochiZutabea] = true;
        tartaLerroa = (int) (Math.random() * 12);
        tartaZutabea = (int) (Math.random() * 12);
        if(osoGertu(tartaLerroa, tartaZutabea))
        {
            while(osoGertu(tartaLerroa, tartaZutabea))
            {
                tartaLerroa = (int) (Math.random() * 12);
                tartaZutabea = (int) (Math.random() * 12);
            }
        }
        setChanged();
        System.out.println("4");
        notifyObservers("tamagochietaTartaHasieratu");
    }
    public  void laukiaAktualizatu(int lerroa, int zutabea)
    {
        System.out.println("bai");
        //PROBLEMA: cuando llega al notifyObservers no salta a el update de minijokoaBista y no se porque
        //si te fijas cuando ejecutas el codigo funciona bien pero no se actualiza la vista
        // esto puedes saberlo por los prints qie he puesto pq si pasas dos veces el raton por encima de 
        //una casilla esta perdera fuerzas, pero lo q pasa que no se actualiza la vista.
        //EN RESUMEN, mirar pq no salta del notifyObservers al update de minijokoaBista
        if (laukiak[lerroa][zutabea] == 1)
        {
            laukiak[lerroa][zutabea] = 0;
            System.out.println("ha llegado al obvserver de laukiak aktualizatu de nivel 1");
            setChanged();
            notifyObservers("laukiaAktualizatu");//cambiar el color del panel
            //convertirlo en invisible
        }
        else if (laukiak[lerroa][zutabea] == 2)
        {
            laukiak[lerroa][zutabea] = 1;
            setChanged();
            System.out.println("ha llegado al obvserver de laukiak aktualizatu de nivel 2");
            notifyObservers("laukiaAktualizatu");//cambiar el color del panel

        }
        else if (laukiak[lerroa][zutabea] == 3)
        {
            laukiak[lerroa][zutabea] = 2;
            setChanged();
            System.out.println("ha llegado al obvserver de laukiak aktualizatu de nivel 3");
            notifyObservers("laukiaAktualizatu");//cambiar el color del panel

        }
    }
    public static boolean irabaziDu()
    {
        if (tamagochiLerroa == tartaLerroa && tamagochiZutabea == tartaZutabea)
        {
            return true;

        }
        else
        {
            return false;
        }
    }
    public static boolean osoGertu(int lerroa, int zutabea)
    {
        if (lerroa == tamagochiLerroa && zutabea == tamagochiZutabea)
        {
            return true;
        }
        else if (lerroa == tamagochiLerroa - 1 && zutabea == tamagochiZutabea)
        {
            return true;
        }
        else if (lerroa == tamagochiLerroa + 1 && zutabea == tamagochiZutabea)
        {
            return true;
        }
        else if (lerroa == tamagochiLerroa && zutabea == tamagochiZutabea - 1)
        {
            return true;
        }
        else if (lerroa == tamagochiLerroa && zutabea == tamagochiZutabea + 1)
        {
            return true;
        }
        else if (lerroa == tamagochiLerroa - 1 && zutabea == tamagochiZutabea - 1)
        {
            return true;
        }
        else if (lerroa == tamagochiLerroa - 1 && zutabea == tamagochiZutabea + 1)
        {
            return true;
        }
        else if (lerroa == tamagochiLerroa + 1 && zutabea == tamagochiZutabea - 1)
        {
            return true;
        }
        else if (lerroa == tamagochiLerroa + 1 && zutabea == tamagochiZutabea + 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}