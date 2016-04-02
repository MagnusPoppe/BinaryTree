package tree;


import java.util.LinkedList;

/** Programmet bruker en LinkedList til å lage køsystem.
 * Created by Magnus Poppe Wang on 18.01.2016.
 * @author Magnus Poppe Wang
 */
public class Queue<T> {

    /** Hvorfor LinkedList?
     * Jeg velger å bruke linked list fordi det er en kortere
     * prosess å fjerne første element her enn i en arraylist.
     * Det er kun pekere som endrer hvilket objekt de peker på
     * som endrer seg, hele listen må ikke restruktureres for
     * å unngå nullpunkter med linkedlist.
     */
    LinkedList<T> liste;

    /** Konstruktør:
     * Instansierer liste.
     */
    public Queue() {
        liste = new LinkedList<>();
    }

    /** Enqueue:
     * Legger til element i køen. Denne kommer naturlig sist.
     * @param t
     */
    public void enqueue(T t) {
        liste.add(t);
    }

    /** Dequeue:
     * Fjerner gjeldende element fra køen.
     */
    public void dequeue() {
        liste.remove(0);
    }

    /** Get first:
     * Henter ut objektet som ligger først i køen.
     */
    public T getFirst() {
        return liste.get(0);
    }

}
