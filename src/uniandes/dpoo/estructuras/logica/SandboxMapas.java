package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;



/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista() {
        Collection<String> collection = mapaCadenas.values();
        List<String> listaValores = new ArrayList<>(collection);
        Collections.sort(listaValores); // Sort the list lexicographically
        return listaValores;
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
    	Set<String> set = mapaCadenas.keySet();
    	List<String> listaValores = new ArrayList<>(set);
    	Collections.sort(listaValores);
    	Collections.reverse(listaValores);
    	return listaValores;
    	
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera() {
        List<String> listaValores = getValoresComoLista();
        if (!listaValores.isEmpty()) { 
            return listaValores.get(0); 
        } else {
            return null; 
        }
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
        List<String> listaValores = getValoresComoLista();
        if (!listaValores.isEmpty()) { 
            return listaValores.get(listaValores.size() - 1 ); 
        } else {
            return null; 
        }
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
    	
    	List<String> listaLlaves = getLlavesComoListaInvertida();
    	List<String> llavesEnMayusculas = new ArrayList<>();
    	
    	for (String llave: listaLlaves) {
    		llavesEnMayusculas.add(llave.toUpperCase());
    	}
        return llavesEnMayusculas;
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
    	List<String> listaValores = getValoresComoLista();
    	HashMap<String, Integer> histograma = new HashMap<String, Integer>();
    	
    	for(int i = 0; i<listaValores.size(); i++ ) {
    		if (histograma.containsKey(listaValores.get(i))){
    			histograma.put(listaValores.get(i), (histograma.get(listaValores.get(i)) + 1));
    		}else {
    			histograma.put(listaValores.get(i), 1);
    		}

    	}
    	return histograma.size();
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena(String cadena) {
       
        String key = new StringBuilder(cadena).reverse().toString();
        
        mapaCadenas.put(key, cadena);
    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor(String valor) {
       Iterator<Map.Entry<String, String>> iterator = mapaCadenas.entrySet().iterator();
       while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (valor.equals(entry.getValue())) {
                iterator.remove(); 
            }
        }
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas(List<Object> objetos) {
        mapaCadenas.clear(); // Clear the existing map

        for (Object obj : objetos) {
            String key = obj.toString(); 
            mapaCadenas.put(key, key); 
        }
    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	Iterator<Map.Entry<String, String>> iterator = mapaCadenas.entrySet().iterator();
    	HashMap<String, String> mapanuevo = new HashMap<String, String>();
    	
    	while (iterator.hasNext()) {
    		Map.Entry<String, String> entry = iterator.next();
    		String newKey = entry.getKey().toUpperCase();
    		mapanuevo.put(newKey, entry.getValue());
    	}
    	mapaCadenas = mapanuevo;

    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores(String[] otroArreglo) {
        for (String valor : otroArreglo) {
            if (!mapaCadenas.containsValue(valor)) {
                return false;
            }
        }
        return true;
    }


}
