/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosplanificaciondisco;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author said
 */
public class AlgoritmosPlanificacionDisco {
    
    static Base datos = new Base();
    static int temp = -1;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BK bk = new BK();
        Scanner entrada = new Scanner(System.in);
        System.out.print("Bienvenido, seleccione una opcion \n1)Ingresar informacion *requerido la primer vez\n2)Algoritmo FCFS\n3)Algoritmo SSTF\n4)Algoritmo SCAN\n5)Algoritmo C-SCAN\n6)Algoritmo LOOK\n7)Algoritmo C-LOOK\n8)Ver informacion\n Para terminar ingresa un 9 o cualquier caracter");
        while(validarNumeros(entrada.nextLine())){
            switch(temp){
                case 1:
                    int[] bk_arreglo = llenarDatos();
                    bk.setCopia(bk_arreglo);
                    break;
                case 2:
                    //validarBase();
                    int[] arreglo = llenarDatos();
                    datos.setCola_solicitudes(arreglo);
                    //datos.setCola_solicitudes(bk.getCopia());
                    procesarFCFS();
                    break;
                case 3: 
                    //validarBase();
                    //datos.setCola_solicitudes(bk.getCopia());
                     arreglo = llenarDatos();
                     datos.setCola_solicitudes(arreglo);
                    procesarSSTF();
                    break;
                case 4: 
                    //validarBase();
                    //datos.setCola_solicitudes(bk.getCopia());
                    arreglo = llenarDatos();
                    datos.setCola_solicitudes(arreglo);
                    procesarSCAN();
                    break;
                case 5: 
                    //validarBase();
                    //datos.setCola_solicitudes(bk.getCopia());
                    arreglo = llenarDatos();
                    datos.setCola_solicitudes(arreglo);
                    procesarCSCAN();
                    break;
                case 6: 
                    //validarBase();
                    //datos.setCola_solicitudes(bk.getCopia());
                    arreglo = llenarDatos();
                    datos.setCola_solicitudes(arreglo);
                    procesarLOOK();
                    break;
                case 7: 
                    //validarBase();
                    //datos.setCola_solicitudes(bk.getCopia());
                    arreglo = llenarDatos();
                    datos.setCola_solicitudes(arreglo);
                    procesarCLOOK();
                    break;
                case 8: 
                    validarBase();
                    imprimirInformacion();
                    break;
                case 9: 
                    System.exit(0); 
            }//switch
            System.out.println();
            System.out.println(" -seleccione una opcion \n1)Ingresar informacion *requerido la primer vez\n2)Algoritmo FCFS\n3)Algoritmo SSTF\n4)Algoritmo SCAN\n5)Algoritmo C-SCAN\n6)Algoritmo LOOK\n7)Algoritmo C-LOOK\n8)Ver informacion\n9)Terminar");
        }//menu
    }//main
    
    public static int[] llenarDatos(){
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Ingrese numero de cilindros...");
        while(!validarNumeros(entrada.nextLine()));
        datos.setCilindros(temp);
        System.out.println("Ingrese numero de solicitudes...");
        while(!validarNumeros(entrada.nextLine()));
        int solicitudes[] = new int[temp];
        int bk[] = new int[temp]; 
        for(int i = 0; i < solicitudes.length; i++){
            System.out.println("Solicitud #"+i+": ");
            if(validarNumeros(entrada.nextLine()))
                if( validarRango(temp, 0, datos.getCilindros() ) ){ //minimo cero maximo lo de cilindros12
                    solicitudes[i] = temp;
                    bk[i] = temp;
                }   
                else i--; // si no entra en el rango entonces solicitar de nuevo
            else i--; // si no ingreso un numero entonces solicitar de nuevo
        }
        datos.setCola_solicitudes(solicitudes);
        System.out.println("Ingrese posicion incial del cabezal...");
        while(!validarNumeros(entrada.nextLine()));
        datos.setPos_inicial(temp);
        
        // Dar a conocer los datos ingresados
        imprimirInformacion();
        return bk;
    }
    
    public static void imprimirInformacion(){
        System.out.println("");
        System.out.print("Cilindros: "+datos.getCilindros()+" Numero de solicitudes: "+datos.getCola_solicitudes().length+" Posicion inicial: "+datos.getPos_inicial());
        int sols[] = datos.getCola_solicitudes();
        System.out.print("\nCola de solicitudes: ");
        for(int i = 0; i < datos.getCola_solicitudes().length; i++)
            System.out.print(sols[i]+" ");
        System.out.println("");
    }
    
    public static boolean validarNumeros(String numero){
        try{
            int n = Integer.parseInt(numero);
            temp = n;
            return true;
        } catch(Exception e){
            System.out.println("Numero invalido. Ingrese otro por favor");
            return false;   
        }
    }
    
    public static boolean validarRango(int numero, int min, int max) {
        if(numero >= min && numero <= max)
            return true;
        else 
            return false;
    }
    
    public static void validarBase(){
        if(datos.getCilindros() == -1 || datos.getCola_solicitudes() == null || datos.getPos_inicial() == -1){
            System.out.println("Sin informacion... Rellena los siguientes datos para continuar");
            llenarDatos();
        }
    }
    
    public static float calcularPromedioTiempo(int tiempo){
        return (float)tiempo / datos.getCola_solicitudes().length;
    }
    
    public static float calcularTiempoPosicionamiento(int desplazamiento){
        return desplazamiento * 6;
    }
    
    public static void imprimirResultados(float prom, float prom2){
        System.out.println("Promedio tiempo espera: "+prom);
        System.out.println("Tiempo de posicionamiento final: "+prom2+"ms");
        System.out.println("Un posicionamiento requiere de 6ms");
    }
    
    /*  Algorimtos  */
    
    public static void procesarFCFS(){
        int prom_tiempo = 0, prom_posicionamiento = 0;
        int[] solicitudes = datos.getCola_solicitudes();
        int t_espera = 0, desplazamiento = Math.abs(solicitudes[0] - datos.getPos_inicial());
        prom_posicionamiento += desplazamiento;
        
        System.out.println();System.out.println();
        System.out.println("SOLICITUD  |  TIEMPO ESPERA  |  DESPLAZAMIENTO");
        System.out.println(solicitudes[0] + "              "+t_espera+"                  "+desplazamiento);
        for(int i = 1; i < solicitudes.length; i++){
            t_espera += desplazamiento;
            desplazamiento = Math.abs(solicitudes[i] - solicitudes[i-1]);
            prom_posicionamiento += desplazamiento;
            prom_tiempo += t_espera;
            System.out.println(solicitudes[i] + "              "+t_espera+"                  "+desplazamiento);
        }
        imprimirResultados(calcularPromedioTiempo(prom_tiempo), calcularTiempoPosicionamiento(prom_posicionamiento) );
        System.out.println();System.out.println();
    }
    
    static int actual = -1; //variable de apoyo en SSTF
    
    public static int encontrarSiguiente(int buscado, int[] x) {
        int distancia = 1000, d, i;
        for (int k = 0; k < x.length; k++) {
            if (actual == x[k])
                x[k] = 5000;
        }
        for (i = 0; i < x.length; i++) {
            d = Math.abs(buscado - x[i]);
            if (d < distancia) {
                distancia = d;
                actual = x[i];
            }
        }
        return actual;
    }
    
    public static void procesarSSTF() {
        int[] x = datos.getCola_solicitudes();
        int[] sstf = new int[x.length];
        int deseado = datos.getPos_inicial();
        for (int i = 0; i < x.length ; i++) {
            sstf[i] = encontrarSiguiente(deseado, x);
            deseado = actual;
        }
        
        int t_espera = 0, desplazamiento = Math.abs(sstf[0] - datos.getPos_inicial());
        int prom_tiempo = 0, prom_posicionamiento = 0;
        prom_posicionamiento += desplazamiento;
        
        System.out.println();System.out.println();
        System.out.println("SOLICITUD  |  TIEMPO ESPERA  |  DESPLAZAMIENTO");
        System.out.println(sstf[0] + "              "+t_espera+"                  "+desplazamiento);
        for(int i = 1; i < sstf.length; i++){
            t_espera += desplazamiento;
            desplazamiento = Math.abs(sstf[i] - sstf[i-1]);
            prom_posicionamiento += desplazamiento;
            prom_tiempo += t_espera;
            System.out.println(sstf[i] + "              "+t_espera+"                  "+desplazamiento);
        }
        imprimirResultados(calcularPromedioTiempo(prom_tiempo), calcularTiempoPosicionamiento(prom_posicionamiento) );
        System.out.println();System.out.println();
    }
    
    public static void  procesarCLOOK() {
        int[] x = datos.getCola_solicitudes();
        int start = datos.getPos_inicial();
        
        int[] temp = new int[x.length];
        int[] temp1 = new int[x.length];
        int a = 0, b = 0;
        for (int i = 0; i < temp.length; i++) {
            if (x[i] > start) {
                temp[a] = x[i];
                a++;
            } else {
                temp1[b] = x[i];
                b++;
            }
        }
        int aa[] = new int[a];
        int[] bb = new int[b];

        aa[0] = start;
        
        for (int i = 0; i < aa.length; i++)
            aa[i] = temp[i];
        
        for (int i = 0; i < bb.length; i++)
            bb[i] = temp1[i];

        Arrays.sort(aa);
        Arrays.sort(bb);

        int c = 0;
        int[] finalX = new int[x.length];
        for (int i = 0; i < aa.length; i++) {
            finalX[c] = aa[i];
            c++;
        }

        for (int i = 0; i < bb.length; i++) {
            finalX[c] = bb[i];
            c++;
        }
        
        int t_espera = 0, desplazamiento = Math.abs(finalX[finalX.length-1] - datos.getPos_inicial());
        int prom_tiempo = 0, prom_posicionamiento = 0;
        prom_posicionamiento += desplazamiento;
        
        System.out.println();System.out.println();
        System.out.println("SOLICITUD  |  TIEMPO ESPERA  |  DESPLAZAMIENTO");
        System.out.println(finalX[finalX.length-1] + "              "+t_espera+"                  "+desplazamiento);
        for(int i = finalX.length-1; i > 0 ; i--){
            t_espera += desplazamiento;
            desplazamiento = Math.abs(finalX[i] - finalX[i-1]);
            prom_posicionamiento += desplazamiento;
            prom_tiempo += t_espera;
            System.out.println(finalX[i-1] + "              "+t_espera+"                  "+desplazamiento);
        }
        imprimirResultados(calcularPromedioTiempo(prom_tiempo), calcularTiempoPosicionamiento(prom_posicionamiento) );
        System.out.println();System.out.println();
    }
    
    public static void procesarLOOK() {
        int[] x = datos.getCola_solicitudes();
        int startingHead = datos.getPos_inicial();
        int[] finalX = new int[x.length+2];
        int[] sortedX = new int[x.length];
        int[] modified;

        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] >= 0 && x[i] < startingHead) {
                sortedX[count] = x[i];
                count++;
            }
        }
        modified = new int[count];

        for (int i = 0; i < modified.length; i++) 
            modified[i] = sortedX[i];

        Arrays.sort(modified);
        int num = 0;
        finalX[num] = startingHead;
        num++;
        for (int i = modified.length - 1; i >= 0; i--) {
            finalX[num] = modified[i];
            num++;
        }
        int[] secondPart = new int[finalX.length];

        int a = 0, b = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] > startingHead) {
                secondPart[b] = x[i];
                b++;
            }
        }

        int abc[] = new int[b];
        for (int i = 0; i < abc.length; i++)
            abc[i] = secondPart[i];

        Arrays.sort(abc);
        for (int i = 0; i < b; i++) {
            finalX[num] = abc[i];
            num++;
        }


        int t_espera = 0, desplazamiento = Math.abs(finalX[1] - datos.getPos_inicial());
        int prom_tiempo = 0, prom_posicionamiento = 0;
        
        prom_posicionamiento += desplazamiento;
        
        System.out.println();System.out.println();
        System.out.println("SOLICITUD  |  TIEMPO ESPERA  |  DESPLAZAMIENTO");
        System.out.println(finalX[1] + "              "+t_espera+"                  "+desplazamiento);
        for(int i = 2; i < finalX.length-1; i++){
            t_espera += desplazamiento;
            desplazamiento = Math.abs(finalX[i] - finalX[i-1]);
            prom_posicionamiento += desplazamiento;
            prom_tiempo += t_espera;
            System.out.println(finalX[i] + "              "+t_espera+"                  "+desplazamiento);
        }
        imprimirResultados(calcularPromedioTiempo(prom_tiempo), calcularTiempoPosicionamiento(prom_posicionamiento) );
        System.out.println();System.out.println();
        
    }
    
    public static void procesarSCAN() {
        int inicio = datos.getPos_inicial();
        int x[] =datos.getCola_solicitudes();
        int n = x.length;
        int scan[] = new int[n + 1];
        int temp[] = new int[n + 1];
        for (int i = 0; i < n; i++)
            temp[i] = x[i];
        
        temp[n] = 0;
        Arrays.sort(temp);
        // find first element in array temp which is less than current
        int index = 0;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] > inicio) {
                index = i - 1;
                break;
            }
        }
        int k = 0;
        
        for (int i = index; i >= 0; --i, ++k) 
            scan[k] = temp[i];
        
        for (int i = index + 1; i < temp.length; i++, ++k) 
            scan[k] = temp[i];
        
        int t_espera = 0, desplazamiento = Math.abs(scan[0] - datos.getPos_inicial());
        int prom_tiempo = 0, prom_posicionamiento = 0;
        prom_posicionamiento += desplazamiento;
        
        System.out.println();System.out.println();
        System.out.println("SOLICITUD  |  TIEMPO ESPERA  |  DESPLAZAMIENTO");
        System.out.println(scan[0] + "              "+t_espera+"                  "+desplazamiento);
        for(int i = 1; i < scan.length; i++){
            t_espera += desplazamiento;
            desplazamiento = Math.abs(scan[i] - scan[i-1]);
            prom_posicionamiento += desplazamiento;
            prom_tiempo += t_espera;
            System.out.println(scan[i] + "              "+t_espera+"                  "+desplazamiento);
        }
        imprimirResultados(calcularPromedioTiempo(prom_tiempo), calcularTiempoPosicionamiento(prom_posicionamiento) );
        System.out.println();System.out.println();
        
    }
    
    public static void procesarCSCAN(){
        int x[] = datos.getCola_solicitudes();
        int inicio = datos.getPos_inicial(), max = datos.getCilindros();
        int key = 0;
        
        Arrays.sort(x);
         
        for(int i = 0; i <x.length; i++)
            if(inicio < x[i]){
                key = i-1;
                break;
            }
        
        for(int i = 0; i < x.length;i++)
            System.out.println(x[i]);
        
        
        int[] f = new int[x.length+2];
        int it = 0;
        for(int i = key; i >= 0; i--){
            f[it] = x[i];
            it++;
        }
        
        f[it++] = 0;
        f[it++] = max-1;
                
        for(int i = x.length-1; i > key;i--){
            f[it]=x[i];
            it++;
        }
        
        
        int t_espera = 0, desplazamiento = Math.abs(f[0] - datos.getPos_inicial());
        int prom_tiempo = 0, prom_posicionamiento = 0;
        
        prom_posicionamiento += desplazamiento;
        
        System.out.println();System.out.println();
        System.out.println("SOLICITUD  |  TIEMPO ESPERA  |  DESPLAZAMIENTO");
        System.out.println(f[0] + "              "+t_espera+"                  "+desplazamiento);
        for(int i = 1; i < f.length; i++){
            t_espera += desplazamiento;
            desplazamiento = Math.abs(f[i] - f[i-1]);
            prom_posicionamiento += desplazamiento;
            prom_tiempo += t_espera;
            System.out.println(f[i] + "              "+t_espera+"                  "+desplazamiento);
        }
        imprimirResultados(calcularPromedioTiempo(prom_tiempo), calcularTiempoPosicionamiento(prom_posicionamiento) );
        System.out.println();System.out.println();
        
    }
}   