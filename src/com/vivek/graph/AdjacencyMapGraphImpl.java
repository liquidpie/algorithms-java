package com.vivek.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by VJaiswal on 11/02/17.
 */
public class AdjacencyMapGraphImpl<V,E> {

    private boolean isDirected;
    private LinkedPositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private LinkedPositionalList<Edge<E>> edges = new LinkedPositionalList<>();

    public AdjacencyMapGraphImpl(boolean directed) { isDirected = directed; }

    public int numVertices() { return vertices.size(); }
    public Iterable<Vertex<V>> vertices() { return vertices; }
    public int numEdges() { return edges.size(); }
    public Iterable<Edge<E>> edges() { return edges; }

    public int outgoingDegree(Vertex<V> v) { return v.getOutgoing().size(); }
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) { return v.getOutgoing().values(); }
    public int incomingDegree(Vertex<V> v) { return v.getIncoming().size(); }
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) { return v.getIncoming().values(); }

    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) { return u.getOutgoing().get(v); }

    public Vertex<V>[] endVertices(Edge<E> e) { return e.getEndpoints(); }

    public Vertex<V> oppositeVertex(Vertex<V> v, Edge<E> e) {
        Vertex<V>[] endpoints = e.getEndpoints();
        return (endpoints[0] == v) ? endpoints[1] : endpoints[0];
    }

    public Vertex<V> insertVertex(V element) {
        Vertex<V> v = new Vertex<>(element, isDirected);
        v.setPosition(vertices.addLast(v));
        return v;
    }

    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
        if (getEdge(u, v) == null) {
            Edge<E> e = new Edge<>(u, v, element);
            e.setPosition(edges.addLast(e));
            u.getOutgoing().put(v, e);
            v.getIncoming().put(u, e);
            return e;
        }
        return null;
    }

    public static <V,E> void DFS(AdjacencyMapGraphImpl<V,E> graph, AdjacencyMapGraphImpl<V,E>.Vertex<V> u,
                          Set<AdjacencyMapGraphImpl<V,E>.Vertex<V>> visited,
                                 Map<AdjacencyMapGraphImpl<V,E>.Vertex<V>, AdjacencyMapGraphImpl<V,E>.Edge<E>> forest) {
        visited.add(u);
        for (AdjacencyMapGraphImpl.Edge e : graph.outgoingEdges(u)) {
            AdjacencyMapGraphImpl<V,E>.Vertex<V> v = graph.oppositeVertex(u, e);
            if (!visited.contains(v)) {
                forest.put(v, e);
                DFS(graph, v, visited, forest);
            }
        }
    }

    public <V,E> LinkedPositionalList<AdjacencyMapGraphImpl<V,E>.Edge<E>> constructPath(AdjacencyMapGraphImpl<V,E> graph,
                                                             AdjacencyMapGraphImpl<V,E>.Vertex<V> u,
                                                             AdjacencyMapGraphImpl<V,E>.Vertex<V> v,
                                                             Map<AdjacencyMapGraphImpl<V,E>.Vertex<V>,
                                                                     AdjacencyMapGraphImpl<V,E>.Edge<E>> forest) {
        LinkedPositionalList<AdjacencyMapGraphImpl<V,E>.Edge<E>> path = new
                LinkedPositionalList<>();
        if (forest.get(v) != null) {
            AdjacencyMapGraphImpl<V,E>.Vertex<V> walk = v;
            while (walk != u) {
                AdjacencyMapGraphImpl<V,E>.Edge<E> edge = forest.get(walk);
                path.addLast(edge);
                walk = graph.oppositeVertex(walk, edge);
            }
        }
        return path;
    }

    public <V,E> void BFS(AdjacencyMapGraphImpl<V,E> graph,
                                 AdjacencyMapGraphImpl<V,E>.Vertex<V> s,
                                 Set<AdjacencyMapGraphImpl<V,E>.Vertex<V>> visited,
                                 Map<AdjacencyMapGraphImpl<V,E>.Vertex<V>,
                                         AdjacencyMapGraphImpl<V,E>.Edge<E>> forest) {
        LinkedPositionalList<AdjacencyMapGraphImpl<V,E>.Vertex<V>> level = new LinkedPositionalList<>();
        level.addLast(s);
        while (!level.isEmpty()) {
            LinkedPositionalList<AdjacencyMapGraphImpl<V,E>.Vertex<V>> nextLevel = new LinkedPositionalList<>();
            for (AdjacencyMapGraphImpl.Vertex u : level) {
                for (Object obj : graph.outgoingEdges(u)) {
                    AdjacencyMapGraphImpl.Edge e = (AdjacencyMapGraphImpl.Edge) obj;
                    AdjacencyMapGraphImpl.Vertex v = graph.oppositeVertex(u, e);
                    if (!visited.contains(v)) {
                        visited.add(v);
                        forest.put(v, e);
                        nextLevel.addLast(v);
                    }
                }
            }
            level = nextLevel;
        }

    }



    // Representation of Vertex
    class Vertex<V> {

        private V element;
        private Position<Vertex<V>> position;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;

        public Vertex(V element, boolean isGraphDirected) {
            this.element = element;
            outgoing = new HashMap<>();
            if (isGraphDirected) {
                incoming = new HashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        public V getElement() { return element; }
        public void setPosition(Position<Vertex<V>> position) { this.position = position; }
        public Position<Vertex<V>> getPosition() { return position; }
        public Map<Vertex<V>, Edge<E>> getOutgoing() { return outgoing; }
        public Map<Vertex<V>, Edge<E>> getIncoming() { return incoming; }

    }

    // Representation of Edge
    class Edge<E> {

        private E element;
        private Position<Edge<E>> position;
        private Vertex<V>[] endpoints;

        public Edge(Vertex<V> u, Vertex<V> v, E element) {
            this.element = element;
            endpoints = (Vertex<V>[]) new Vertex[] {u, v};
        }

        public E getElement() { return element; }
        public Vertex<V>[] getEndpoints() { return endpoints; }
        public void setPosition(Position<Edge<E>> position) { this.position = position; }
        public Position<Edge<E>> getPosition() { return position; }

    }

    // PositionalList Representation

    private class LinkedPositionalList<E> implements Iterable<E> {
        private Node<E> header;
        private Node<E> trailer;
        private int size = 0;

        public LinkedPositionalList() {
            header =  new Node<>(null, null, null);
            trailer = new Node<>(null, header, null);
            header.setNext(trailer);
        }

        private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
            Node<E> node = new Node<>(e, pred, succ);
            pred.setNext(node);
            succ.setPrev(node);
            size++;
            return node;
        }

        public Position<E> addLast(E e) {
            return addBetween(e, trailer.getPrev(), trailer);
        }

        public Iterator<E> iterator() {
            Iterator<E> it = new Iterator<E>() {

                private int currentIndex = 0;
                private Node<E> currentNode = header;

                @Override
                public boolean hasNext() {
                    return currentIndex < size;
                }

                @Override
                public E next() {
                    currentIndex++;
                    currentNode = currentNode.getNext();
                    return currentNode.getElement();
                }
            };

            return it;
        }

        public int size() { return size; }

        public boolean isEmpty() { return size == 0; }

        private class Node<E> implements Position<E> {
            private E element;
            private Node<E> prev;
            private Node<E> next;

            public Node(E e, Node<E> p, Node<E> n) {
                element = e;
                prev = p;
                next = n;
            }

            public E getElement() { return element; }
            public void setElement(E e) { element = e; }
            public Node<E> getPrev() { return prev; }
            public void setPrev(Node<E> prev) { this.prev = prev; }
            public Node<E> getNext() { return next; }
            public void setNext(Node<E> next) { this.next = next; }
        }

    }

    interface Position<E> {
        E getElement();
    }

}
