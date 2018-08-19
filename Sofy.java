/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosplanificaciondisco;

import static algoritmosplanificaciondisco.AlgoritmosPlanificacionDisco.datos;
import java.util.Arrays;

/**
 *
 * @author said
 */
public class Sofy {
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
        
        System.out.println();System.out.println();
        
    }
}
