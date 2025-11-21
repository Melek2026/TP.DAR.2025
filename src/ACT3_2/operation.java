package ACT3_2;

import java.io.Serializable;

public class operation implements Serializable {
    private static final long serialVersionUID = 1L; // bonne pratique pour Serializable

    public int opd1;   // le premier opérande
    public int opd2;   // le deuxième opérande
    public String op;  // l’opérateur (+, -, *, /)
    public float res;  // le résultat de l’opération

    // Constructeur
    public operation(int opd1, int opd2, String op) {
        this.opd1 = opd1;
        this.opd2 = opd2;
        this.op = op;
    }

    // Méthodes d'opération
    public float somme() {
        return opd1 + opd2;
    }

    public float soustraction() {
        return opd1 - opd2;
    }

    public float multiplication() {
        return opd1 * opd2;
    }

    public float division() {
        if (opd2 == 0) {
            System.out.println("⚠️ Division par zéro !");
            return 0;
        }
        return (float) opd1 / opd2;
    }
}
