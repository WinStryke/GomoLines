/**
 * Created by stryker on 06/12/16.
 */
public class JoueurColorLines extends Joueur{

    public JoueurColorLines(String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
    }

    @Override
    public void setScore(int n) {

    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public String getPseudo() {
        return null;
    }

    @Override
    public String[] jouer() {
        return new String[0];
    }
}