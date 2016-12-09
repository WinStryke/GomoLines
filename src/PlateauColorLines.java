import java.util.Random;
import java.util.Scanner;

/**
 * Created by Stryker on 04/12/2016.
 */
public class PlateauColorLines extends Plateau {
    protected Joueur j1;

    public PlateauColorLines(int dimension, JoueurColorLines j1) {
        super(dimension);
        cases = new Case[dimension][dimension];
        this.j1 = j1;
    }
    public PlateauColorLines(int dimension) {
        super(dimension);
    }


    @Override
    public int[] robot() {
        return new int[0];
    }

    public void putThreeColors() {
        int cmp = 0;
        while (cmp != 3) {
            int a = new Random().nextInt(longueur);
            int b = new Random().nextInt(largeur);
            while (!cases[a][b].isEmpty()) {
                a = new Random().nextInt(longueur);
                b = new Random().nextInt(largeur);
            }
            cases[a][b].pion = new PionColorLines();
            cmp++;
        }
    }

    @Override
    protected void faire(int x, int y, boolean joueur) {}

    public void launch(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel pions souhaitez vous déplacez ?");
        String [] t = sc.nextLine().split(",");
        int a = Integer.parseInt(t[0]);
        int b = Integer.parseInt(t[1]);
        System.out.println("Ou souhaitez vous déplacez le pion ?");
        String [] z = new Scanner(System.in).nextLine().split(",");
        faire(Integer.parseInt(z[0]),Integer.parseInt(z[1]),cases[a][b]);
    }

    protected void faire(int x, int y, Case c){
        if (x >= 0 && x <= cases.length && y >= 0 && y < cases.length) {
            for (int i = 0; i < cases.length; i++) {
                for (int j = 0; j < cases[i].length; j++) {
                    if (x == i && y == j) {
                        if (cases[i][j].isEmpty()) {
                            switch (c.getPion().toString()) {
                                case "rouge":
                                    cases[i][j].fabrique("rouge");
                                    break;
                                case "vert":
                                    cases[i][j].fabrique("vert");
                                    break;
                                case "bleu":
                                    cases[i][j].fabrique("bleu");
                                    break;
                                case "arcenciel":
                                    cases[i][j].fabrique("arcenciel");
                                    break;
                            }
                            c.setPion(null);
                        } else {
                            System.out.println("Coup interdit!");
                            System.out.println("La case n'est pas vide!");
                            afficher();
                            System.out.println("Veuillez rentrer à nouveau des valeurs: ");
                            String s = new Scanner(System.in).nextLine();
                            String[] t = s.split(",");
                            faire(Integer.parseInt(t[0]), Integer.parseInt(t[1]), c);
                        }
                    }
                }
            }
        }else{
            System.out.println("Valeurs incorrectes!");
            afficher();
            System.out.println("Veuillez entrer à nouveau des valeurs:");
            String s = new Scanner(System.in).nextLine();
            String[] t = s.split(",");
            faire(Integer.parseInt(t[0]), Integer.parseInt(t[1]), c);
        }
    }

    @Override
    public void afficherScore() {

    }

    @Override
    public Case[][] getCases() {
        return this.cases;
    }

    @Override
    public void jouer(String s) {
        switch (s) {
            case "classique":
                this.putThreeColors();
                this.afficher();
                this.launch();
                break;
        }
    }

    public static void main(String[] args) {
        Plateau a = new PlateauColorLines(5, new JoueurColorLines("yolo"));
        a.initializeThePlate();
        while (!a.isFull()) {
            a.jouer("classique");
        }
    }
}
