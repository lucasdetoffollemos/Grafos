package grafos;

import java.util.ArrayList;
import java.util.Collections;
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
    
    
    
     public static ArrayList<Vertice> algoritmoDijkstra(Grafo g, Vertice verticeInicial) {
        ArrayList<Vertice> resultado = g.obterVertices();
        ArrayList<Vertice> verticesZerados = new ArrayList();
        
        ArrayList<Vertice> verticesVisitado = new ArrayList();

        for(Vertice vertice : g.obterVertices()){
            if(vertice != verticeInicial){
                verticesZerados.add(vertice);
            }
        }
        
        for(Vertice vertice : verticesZerados){
            vertice.setCaminho("");
            vertice.zerarDistancia();
        }
        
        verticeInicial.setCaminho(verticeInicial.toString());
        verticeInicial.definirDistancia(0);
        
       ArrayList<Vertice> S = new ArrayList<Vertice>();
        
        
        ArrayList<Vertice> Q = g.obterVertices();
        
        while(Q.size() != 0){
            Vertice u = removerComMenorDistancia(Q);
            
            Q.remove(u);
            
            S.add(u);
            
            /**  **/
            
            ArrayList<Vertice> adjacencia = new ArrayList();
            
            
             
             u.setCaminhoLista(g.obterTodosOsArcos());
             
             
             ArrayList<Arco> arcos = u.getCaminhoLista();
             
            for(Arco arco : arcos){
                if(u == arco.getOrigem()){
                    adjacencia.add(arco.getDestino());
                }
            }
        
            
            /**  **/
            verticesVisitado.add(u);

            
            for(Vertice vertice : adjacencia){
               if(vertice.obterDistancia() > u.obterDistancia() + getPeso(u, vertice)){
                   vertice.definirDistancia((u.obterDistancia() + getPeso(u, vertice)));
                   vertice.setCaminho(u.getCaminho());
               }
            }
            
        }
       
        return verticesVisitado;
    }
     
     
    private static double getPeso(Vertice u, Vertice v){
        
        double peso = 0;
        
        ArrayList<Arco> arcos  = u.obterArcos();
        
        for(Arco arco : arcos)
        {
            if(arco.getDestino().equals(v))
            {
                peso = arco.getPeso();
                return peso;
            }
        }
        
        return peso;
        
       
    }
     
     private static Vertice removerComMenorDistancia(ArrayList<Vertice> vertices){
         
        Vertice vMenorDistancia = vertices.get(0);
        for (Vertice v : vertices) {
            if (vMenorDistancia.obterDistancia() >= v.obterDistancia()) {
                vMenorDistancia = v;
            }
        }
        return vMenorDistancia;
         
        
     }
     
     public static ArrayList<Arco> algoritmoKruskall(Grafo g) {
         
         
         ArrayList<Arco> resultado = new ArrayList<Arco>();
        //pegar todos os vertices 
        
        ArrayList<Vertice> todosVertices = g.obterVertices();

       
         //achando vertcice que tem menor peso
         ArrayList<Arco> arcos = g.obterTodosOsArcos();
        
         
         double menor = arcos.get(0).getPeso();
         Vertice verticeOrigem;
         Vertice verticeDestino;
         
         Arco arcoMenorPeso = arcos.get(0);
         
         
         //ordenando os arcos
         Collections.sort(arcos);
         


         for (int i = 0; i < arcos.size(); i++) {
             //montar as ligaçoes, g com h
             
            
            
            
            
             if(arcos.get(i).getOrigem().getnArvore() == 0 && arcos.get(i).getDestino().getnArvore() == 0){
                 arcos.get(i).getOrigem().setnArvore(1);
                 arcos.get(i).getDestino().setnArvore(1);
                 resultado.add(arcos.get(i));
                 
                
             }
             
             else if((arcos.get(i).getOrigem().getnArvore() == 1 && arcos.get(i).getDestino().getnArvore() == 0) || (arcos.get(i).getOrigem().getnArvore() == 0 && arcos.get(i).getDestino().getnArvore() == 1)) {
                 arcos.get(i).getOrigem().setnArvore(1);
                 arcos.get(i).getDestino().setnArvore(1);
                 resultado.add(arcos.get(i));
             }
             
             else{
                 //veroficar se o vertice irá fazer ciclos.
                 //se não fizer adicionar no  array resultado 
               if(arcos.get(i).getOrigem().obterGrau() == 2 ){
                   
                   boolean arcoAdcionado = arcoAdcionado(resultado, arcos.get(i));
                   if(arcoAdcionado == false){
                       resultado.add(arcos.get(i));
                   }
               }
             }
             
         }


        
        return resultado;
         
    }
     
     
     //método criado para melhor visualização do algoritmo
     private static void obterTodosOsArcos(ArrayList<Arco> arcos) {
       
       
       for(Arco arco : arcos){
           System.out.println(arco);
        }
        System.out.println("");
    }

     //método criado para melhor visualização do algoritmo
    private static void obterArcosDoVertice(ArrayList<Arco> arcos) {
        
          for (int i = 0; i < arcos.size(); i++) {
             System.out.println("Peso do arco: " + arcos.get(i).getPeso() + " Vértice de origem " + arcos.get(i).getOrigem() + " Vértice de destino " + arcos.get(i).getDestino());
             
         }
          
          System.out.println("");
    }

    private static boolean arcoAdcionado(ArrayList<Arco> arcos,  Arco arco) {
        
        for (int i = 0; i < arcos.size(); i++) {
            
            if(arcos.get(i).getOrigem() == arco.getDestino() && arcos.get(i).getDestino() == arco.getOrigem()){
                return true;
            }
            
        }
        return false;
    }
    
   

    
    
    
    
  
     
     
     
    
    
}
