package BST;

public interface Dictionary<K,V> {
    /**
     * Returnerer elementet hørende til nøglen k. Hvis nøglen ikke findes,
     * returneres null
     */
    public V get(K key);

    /**
     * Returnerer om dictionary er tom.
     */
    public boolean isEmpty();

    /**
     * Indsætter objektet value med nøgle key i dictionary. Hvis key allerede
     * eksisterer, overskrives value hørende til den key, og den gamle value
     * returneres. Hvis key ikke findes, returneres null. Hverken key eller
     * value må være null
     */
    public V put(K key, V value);

    /**
     * Fjerner (nøgle, værdi)-parret med nøglen key fra dictionary'en og
     * value returneres. Hvis key ikke findes, returneres null.
     */
    public V remove(K key);

    /**
     * Returnerer antallet af elementer i dictionary
     */
    public int size();
}

