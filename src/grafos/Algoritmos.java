package grafos;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Nesta classe devem ser implementados todos os métodos de grafos de forma estática
 * @author vilson.junior
 */
public class Algoritmos {
    
    public static ArrayList<Vertice> percorreLargura(Grafo g, Vertice verticeInicial) {
        ArrayList<Vertice> resultado = g.obterVertices();
        ArrayList<Vertice> verticesZerados = new ArrayList();
        
        ArrayList<Vertice> verticesVisitado = new ArrayList();

        for(Vertice vertice : g.obterVertices()){
            if(vertice != verticeInicial){
                verticesZerados.add(vertice);
            }
        }
        
        for(Vertice vertice : verticesZerados){
            vertice.zerarVisitas();
            vertice.zerarDistancia();
        }
        
        verticeInicial.visitar();
        verticeInicial.definirDistancia(0);
        
        Queue<Vertice> queue = new LinkedList<>();
        
        queue.add(verticeInicial);
        
        
       
        
        while(queue.size() != 0){
            Vertice u = queue.remove();
            
            ArrayList<Vertice> adjacencia = new ArrayList();
            
             /**  **/
             
             u.setCaminhoLista(g.obterTodosOsArcos());
             
             
             ArrayList<Arco> arcos = u.getCaminhoLista();
             
            for(Arco arco : arcos){
                if(u == arco.getOrigem()){
                    adjacencia.add(arco.getDestino());
                }
            }
        
            
            /**  **/
            verticesVisitado.add(u);
            
            
            double distancia;
            for(Vertice vertice : adjacencia){
                if(vertice.obterVisitado() == 0){
                    distancia = obterDistancia(u, vertice, arcos);
                    vertice.visitar();
                    vertice.definirDistancia(vertice.obterDistancia() + distancia);
                    queue.add(vertice);
                }
            }
            
        }
        
        
        
        
        return verticesVisitado;
    }
    
    public static ArrayList<Vertice> percorreProfundidade(Grafo g, Vertice verticeInicial) {
        ArrayList<Vertice> resultado = g.obterVertices();
        ArrayList<Vertice> verticesZerados = new ArrayList();
        
        ArrayList<Vertice> verticesVisitado = new ArrayList();

        for(Vertice vertice : g.obterVertices()){
            if(vertice != verticeInicial){
                verticesZerados.add(vertice);
            }
        }
        
        for(Vertice vertice : verticesZerados){
            vertice.zerarVisitas();
            vertice.zerarDistancia();
        }
        
        verticeInicial.visitar();
        verticeInicial.definirDistancia(0);
        
        Stack<Vertice> stack = new Stack<>();
        
        stack.push(verticeInicial);
        
        
       
        
        while(stack.size() != 0){
            Vertice u = stack.pop();
            
            ArrayList<Vertice> adjacencia = new ArrayList();
            
          
             
             u.setCaminhoLista(g.obterTodosOsArcos());
             
             
             ArrayList<Arco> arcos = u.getCaminhoLista();
             
            
             
            for(Arco arco : arcos){
                if(u == arco.getOrigem()){
                    adjacencia.add(arco.getDestino());
                }
            }
            
      
            
           
            
            verticesVisitado.add(u);
            
            double distancia;
            
            for(Vertice vertice : adjacencia){
                if(vertice.obterVisitado() == 0){
                    distancia = obterDistancia(u, vertice, arcos);
                    vertice.visitar();
                    vertice.definirDistancia(vertice.obterDistancia() + distancia);
                    stack.push(vertice);
                }
            }
            
        }

        return verticesVisitado;
    }
    
    private static double obterDistancia(Vertice origem, Vertice destino, ArrayList<Arco> arcos){
        
            double distancia = 0;
            for(int i = 0; i < arcos.size(); i++){
                if(origem == arcos.get(i).getOrigem() && destino == arcos.get(i).getDestino()){
                    distancia = arcos.get(i).getPeso();
                }
            }
        
        
        return distancia;
    }
    
    
}
