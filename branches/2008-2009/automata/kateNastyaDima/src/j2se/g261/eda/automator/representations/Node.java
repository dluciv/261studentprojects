/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.representations;

import java.util.Vector;

/**
 *
 * @author nastya
 */
public abstract class Node<NameType> {

    protected int number;
    protected NameType name;
    protected Vector<Node<NameType>> incoming;
    protected Vector<Node<NameType>> outgoing;

    protected Node(NameType name, int number) {
        this.name = name;
        this.number = number;
        this.outgoing = new Vector<Node<NameType>>();
        this.incoming = new Vector<Node<NameType>>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public NameType getName() {
        return name;
    }

    public void setName(NameType name) {
        this.name = name;
    }

    public void addIncomingNode(Node<NameType> n) {
        if (!incoming.contains(n)) {
            incoming.add(n);
        }
    }

    public void addOutgoingNode(Node<NameType> n) {
        if (!outgoing.contains(n)) {
            outgoing.add(n);
        }
    }

    public void removeNodeFromIncoming(Node<NameType> n) {
        incoming.remove(n);
    }

    public void removeNodeFromOutgoing(Node<NameType> n) {
        outgoing.remove(n);
    }

    public boolean haveIncoming() {
        return incoming.size() != 0;
    }

    public boolean haveOutgoing() {
        return outgoing.size() != 0;
    }

    public int getOutgoingSize() {
        return outgoing.size();
    }

    public int getIncomingSize() {
        return incoming.size();
    }

    public Node<NameType> getIncomingAt(int index) {
        return (Node<NameType>) incoming.get(index);
    }

    public Node<NameType> getOutgoingAt(int index) {
        return (Node<NameType>) outgoing.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node<NameType> other = (Node<NameType>) obj;
        if (this.number != other.number) {
            return false;
        }
        return true;
    }
}
