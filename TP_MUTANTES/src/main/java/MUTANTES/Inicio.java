package MUTANTES;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author hiroj
 */
public class Inicio {
    public static void main(String[] args) {
        String[] DNA = VerificacionDeCondicionesString();
        String Opcion = "";
        
        for(int i = 0;i<DNA.length;i++){
            System.out.println("DNA ["+i+"] :" + DNA[i]);
        }
        System.out.println("______________________________________");
        boolean esMutante = isMutant(DNA);
        
        if(esMutante == true){
            Opcion = "SI";
        }else{
            Opcion = "NO";
        }
        JOptionPane.showMessageDialog(null, "Es mutante? "+Opcion);
        System.out.println("Es mutante? "+Opcion);
    }
    
    //Es mutante?
    public static boolean isMutant(String[] DNA){ //Detectar si es mutante
        //VARIABLES
        boolean esMutante = false;
        int contador = 0;
        ArrayList<String> Registro = new ArrayList<String>();
        String[] Palabras = {"AAAA","TTTT","GGGG","CCCC"};
        
        //OPERACIONES
        String[][] MATRIZ =CrearMatriz(DNA);
        
        MostrarValores(MATRIZ);
        
        //Buscamos la cantidad de veces que se encunetran las secuencias
        Registro =  RecorrerHorizontal(MATRIZ);
        contador = ContadorPalabrasContenidasListas(Registro,Palabras);
        Registro =  RecorrerVertical(MATRIZ);
        contador = contador + ContadorPalabrasContenidasListas(Registro,Palabras);
        Registro =  RecorrerOblicuamentesIDI(MATRIZ);
        contador = contador + ContadorPalabrasContenidasListas(Registro,Palabras);
        Registro =  RecorrerOblicuamentesDII(MATRIZ);
        contador = contador + ContadorPalabrasContenidasListas(Registro,Palabras);
        System.out.println("Cantidad TOTAL de coincidencias: " + contador);
        
        //RETORNO
        if(contador > 1){
            esMutante = true;
            return esMutante;
        }else{
            esMutante = false;
            return esMutante;
        }
    }
    
    //MATRIZ
    public static String[][] CrearMatriz(String[] DNA){
        //Tamaño de matriz
        int j = 6;
        int cont = 0;
        String[][] MATRIZ = new String[j][j];
        
        for(int i=0;i<j;i++){
            String dna = DNA[i];
            MATRIZ[i] = SepararLetras(dna); //i: columnas y j: filas
        }
        
        return MATRIZ;
    }
    
    public static int ContadorPalabrasContenidasListas(ArrayList<String> Registro,String[] Palabras){ //Devuelve el total de veces que har repetida una/unas palabras en una lista
        int contador = 0;
        for(int j = 0 ;j<Palabras.length;j++){
            for(int i = 0 ;i<Registro.size();i++){
                if(Registro.get(i).contains(Palabras[j])){
                    contador++;
                }
            }
        }
        
        return contador;
    }
    
    public static ArrayList RecorrerHorizontal(String arreglo[][]){
        //Variables
        ArrayList<String> Registro = new ArrayList<String>();
        String secuencia = "";
        
        //OPERACION
        for(int j = 0; j<arreglo.length;j++){
            for(int i = 0; i<arreglo[j].length;i++){
                secuencia = secuencia + arreglo[i][j];
            }
            Registro.add(secuencia);
            secuencia = "";
        }
        
        //RETORNO
        return Registro;
    }
    
    public static ArrayList RecorrerVertical(String arreglo[][]){
        //Variables
        ArrayList<String> Registro = new ArrayList<String>();
        String secuencia = "";
        
        //OPERACION
        for(int j = 0; j<arreglo.length;j++){
            for(int i = 0; i<arreglo[j].length;i++){
                secuencia = secuencia + arreglo[j][i];
            }
            Registro.add(secuencia);
            secuencia = "";
        }
        
        //RETORNO
        return Registro;
    }
    
    public static ArrayList RecorrerOblicuamentesIDI(String arreglo[][]){ //Recorre la matriz de forma oblicua de izquierda a derecha desde el extremo INFERIOR
        int cont = -(arreglo.length+1);
        ArrayList<String> Registro = new ArrayList<String>();
        String secuencia = "";
        do{
            for (int i = arreglo.length - 1; i >= 0; i--) {
                for (int j = arreglo[i].length - 1; j >= 0; j--) {
                    if (i + j == arreglo.length+cont) {
                        secuencia = secuencia + arreglo[i][j];
                    }
                }
            }
        Registro.add(secuencia);
        secuencia = "";
        cont++;
        }while(cont != arreglo.length-1);
        
        return Registro;
    }
    
    public static ArrayList RecorrerOblicuamentesDII(String arreglo[][]){ //Recorre la matriz de forma oblicua de izquierda a derecha desde el extremo INFERIOR
        int cont = -(arreglo.length);
        ArrayList<String> Registro = new ArrayList<String>();
        String secuencia = "";
        do{
            for (int i = arreglo.length - 1; i >= 0; i--) {
                for (int j = arreglo[i].length - 1; j >= 0; j--) {
                    if (j + cont == i) {
                        secuencia = secuencia + arreglo[i][j];
                    }
                }
            }
        Registro.add(secuencia);
        secuencia = "";
        cont++;
        }while(cont != arreglo.length);
        
        return Registro;
    }
    
    public static void MostrarValores(String arreglo[][]){
        //-----------------------Mostramos los valores----------------------------------------------
        for(int j = 0; j < arreglo[0].length; j++){ //Filas (invertido)
            System.out.print("[");
            for(int i = 0; i < arreglo.length; i++){ //Columnas (invertido)
                System.out.print(arreglo[j][i]); //(invertido)
                if(i < (arreglo.length-1)){
                    System.out.print(" , ");
                }
            }
            System.out.println("]");
        }
    }
    
    //VERIFICACION DE INPUT STRING:
    public static String[] VerificacionDeCondicionesString(){ //Para verificar que cumplan con las condiciones del String
        //VARIABLES
        String dna = "";
        int tamano = 6;
        String[] DNA = new String[tamano];
        boolean verifica = false;
        
        //LOOPS
        for(int i = 0;i<tamano;i++){
            do{
            dna = VerificacionDeLargoCadena("Ingrese DNA de la persona [Letras:A,T,C,G] | [Tamaño:6] :",6);
            verifica = VerificacionDeLetras(dna);
            }while(verifica == false);
            DNA[i] = dna;
        }
        
        //RETORNO
        return DNA;
    }
    
    public static boolean VerificacionDeLetras(String texto){
        boolean letrasCorrectas = false;
        String[] TEXTO = SepararLetras(texto);
        for(int i=0;i<TEXTO.length;i++){
            //System.out.println("LETRA: " + TEXTO[i]);
            //Usando switch
            switch (TEXTO[i]) {
                case "A":
                    letrasCorrectas = true;
                    break;
                case "T":
                    letrasCorrectas = true;
                    break;
                case "C":
                    letrasCorrectas = true;
                    break;
                case "G":
                    letrasCorrectas = true;
                    break;
                default:
                    letrasCorrectas = false;
                    break;
            }
            if(letrasCorrectas == false){
                break;
            }
            //Usando IF
//            if(TEXTO[i].equals("A") == true){
//                letrasCorrectas = true;
//            }else if(TEXTO[i].equals("T") == true){
//                letrasCorrectas = true;
//            }else if(TEXTO[i].equals("C") == true){
//                letrasCorrectas = true;
//            }else if(TEXTO[i].equals("G") == true){
//                letrasCorrectas = true;
//            }else{
//                letrasCorrectas = false;
//                break;
//            }
        }
        //System.out.println("VERIFICA: " + letrasCorrectas);
        if(letrasCorrectas == false){
            JOptionPane.showMessageDialog(null, "Hay por lo menos una letra que no corresponde");
        }
        
        return letrasCorrectas;
    }
    
    public static String VerificacionDeLargoCadena(String texto,int tamano){ //Para que no incerten caracteres de mas;
        String text = "";
        do{
            text = InputDialogNoVacio(texto);
            if(tamano != text.length()){
                JOptionPane.showMessageDialog(null, "El tamaño de la texto debe ser de: " + tamano);
            }
        }while(tamano != text.length());
   
        return text;
    }
    
    public static String InputDialogNoVacio(String texto){ //Para que no incerten un espacio en blanco en los datos
        String Input = "";
        do{
            Input = JOptionPane.showInputDialog(texto);
            if(Input == null || Input.equals("") == true){
                JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN DATO");
                Input = "";
            }
        }while(Input.equals("") == true); //V
        return Input;
    } //Nos devuelve obligatoriamente un Sring, No acepta que el usuario no cargue datos
    
    public static String[] SepararLetras(String texto){
        
        char[] DNA = texto.toCharArray();
        String Mayuscula = "";//CONVERTIR EN MAYUSCULA
        String[] arregloDeCadenas = new String[DNA.length];

        for (int i = 0; i < DNA.length; i++) {
            Mayuscula = Character.toString(DNA[i]); //en caso que no se quiera convertir en mayuscula: arregloDeCadenas[i] = Character.toString(DNA[i]);
            arregloDeCadenas[i] = Mayuscula.toUpperCase(); //CONVERTIR EN MAYUSCULA
        }
        return arregloDeCadenas;
    }
}
