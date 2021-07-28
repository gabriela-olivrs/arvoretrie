package Trie;


import java.util.*;

/**
 *
 * @author Daniela
 * @param <Value>
 */
public class Trie<Value>{
   private static int R = 256; // "base": tamanho do alfabeto
   private Node root;          // raiz da trie

   private static class Node{
      Object val;
      Node[] next  = new Node[R];
   }	

   public Value get(String key){ // Pesquisa Key 
       Node x = get(root, key, 0);
       if (x == null) return null;
       return (Value) x.val;
   }

   private Node get(Node x, String key, int d){ // Versão privada de get
      if (x == null) 
          return null;
      if (d == key.length()) 
          return x;
      char c = key.charAt(d);
      return get(x.next[c], key, d+1);
   }

   public void put(String key, Value val){ // Insere uma entrada
        root = put(root, key, val, 0); 
   }

   private Node put(Node x, String key, Value val, int d){ // Versão privada de put
      if (x == null) x = new Node();
      if (d == key.length()) { 
         x.val = val; 
         return x; 
      }
      char c = key.charAt(d);
      x.next[c] = put(x.next[c], key, val, d+1);
      return x;
   }

   public void delete(String k){ // remove a entrada de chave = K
        root = delete(root, k, 0); 
   }
 
   private Node delete(Node x, String k, int d) { //remove recursivo
      if (x == null) return null;
      if (d == k.length())
         x.val = null;
      else {
         char c = k.charAt(d);
         x.next[c] = delete(x.next[c], k, d+1);
      }
      if (x.val != null) return x;
      for (char c = 0; c < R; c++)
         if (x.next[c] != null) return x;
      return null;
   }
    
   public Iterable<String> keysWithPrefix(String pre) {
      Queue<String> q = new ArrayDeque<String>();
      collect(get(root, pre, 0), pre, q);
      return q;
   }

   private void collect(Node x, String pre, Queue<String> q) {
       if (x == null) return;
       if (x.val != null) q.add(pre);
       for (char c = 0; c < R; c++)
          collect(x.next[c], pre + c, q);
   }

  
    public static void main(String[] args) {
             Trie<Integer> t = new Trie<>();
             t.put("segunda", 1);
             t.put("se", 1);
             t.put("sa", 1);
             for (String s: t.keysWithPrefix("s")){
                 System.out.println("Chave: "+s);
             }
             
         // System.out.println("Prefixo mais longo em 'segundao' que eh chave: "+t.longestPrefixOf("segundao"));
         //   System.out.println("\nChaves que fazem match com 's.g.nda': ");
         //   for(String s: t.keysThatMatch("s.g.nda")){
         //     System.out.println("Chave: "+s);
         //
    }
    
}
