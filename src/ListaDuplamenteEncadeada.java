import java.util.NoSuchElementException;

public class ListaDuplamenteEncadeada {
    private static class Nodo {
        public int valor;
        public Nodo proximo;
        public Nodo anterior;

        public Nodo(int valor) {
            this.valor = valor;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private Nodo primeiro;
    private Nodo ultimo;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    public void insereInicio(int valor) {
        Nodo novoNodo = new Nodo(valor);
        if (primeiro == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            novoNodo.proximo = primeiro;
            primeiro.anterior = novoNodo;
            primeiro = novoNodo;
        }
        tamanho++;
    }

    public void removeInicio() {
        if (primeiro == null) {
            throw new NoSuchElementException("A lista está vazia");
        } else {
            primeiro = primeiro.proximo;
            if (primeiro != null)
                primeiro.anterior = null;
            tamanho--;
            if (tamanho == 0) {
                ultimo = null;
            }
        }
    }

    public void insereFinal(int valor) {
        Nodo novoNodo = new Nodo(valor);
        if (ultimo == null) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            ultimo.proximo = novoNodo;
            novoNodo.anterior = ultimo;
            ultimo = novoNodo;
        }
        tamanho++;
    }

    public void removeFinal() {
        if (ultimo == null) {
            throw new NoSuchElementException("A lista está vazia");
        } else {
            if (primeiro == ultimo) {
                primeiro = null;
                ultimo = null;
            } else {
                ultimo = ultimo.anterior;
                ultimo.proximo = null;
            }
            tamanho--;
        }
    }

    public void inserePosicao(int valor, int posicao) {
        if (posicao < 0 || posicao > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (posicao == 0) {
            insereInicio(valor);
        } else if (posicao == tamanho) {
            insereFinal(valor);
        } else {
            Nodo novoNodo = new Nodo(valor);
            Nodo atual = primeiro;
            for (int i = 0; i < posicao - 1; i++) {
                atual = atual.proximo;
            }
            novoNodo.proximo = atual.proximo;
            novoNodo.anterior = atual;
            atual.proximo.anterior = novoNodo;
            atual.proximo = novoNodo;
            tamanho++;
        }
    }

    public void removePosicao(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if (posicao == 0) {
            removeInicio();
        } else if (posicao == tamanho - 1) {
            removeFinal();
        } else {
            Nodo atual = primeiro;
            for (int i = 0; i < posicao; i++) {
                atual = atual.proximo;
            }
            atual.anterior.proximo = atual.proximo;
            atual.proximo.anterior = atual.anterior;
            tamanho--;
        }
    }

    public void imprimirLista() {
        Nodo atual = primeiro;
        System.out.print("Lista: ");
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    // Retorna a lista em uma string com elementos separados por espaço
    public String getInlineList() {
        StringBuilder str = new StringBuilder();
        Nodo atual = primeiro;
        while (atual != null) {
            str.append(atual.valor).append(" ");
            atual = atual.proximo;
        }
        return str.toString();
    }
}