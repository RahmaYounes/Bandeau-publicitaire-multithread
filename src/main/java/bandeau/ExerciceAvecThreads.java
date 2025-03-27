package bandeau;
import jdk.jfr.Threshold;

public class ExerciceAvecThreads {

    public static void main(String[] args) {
        ExerciceAvecThreads instance = new ExerciceAvecThreads();
        instance.exemple();
    }

    public void exemple() {

        Scenario s = makeScenario();
        // On cree les bandeaux
        Bandeau b1 = new Bandeau();
        Bandeau b2 = new Bandeau();
        Bandeau b3 = new Bandeau();
        System.out.println("CTRL-C pour terminer le programme");
        
        // On doit jouer le scénario en même temps sur les trois bandeaux

        // On crée un thread pour chaque bandeau
        /**s.playOn(b1);
        s.playOn(b2);
        s.playOn(b3);
//         On rejoue le scénario sur b1 quand le premier jeu est fini
        s.playOn(b1);**/

        for(int i=0; i <=2; i++){
            BandeauWithThreads bandeauWithThreads1 = new BandeauWithThreads(b1, s);
            BandeauWithThreads bandeauWithThreads2 = new BandeauWithThreads(b2, s);
            BandeauWithThreads bandeauWithThreads3 = new BandeauWithThreads(b3, s);

            bandeauWithThreads1.scenarioStart();
            bandeauWithThreads2.scenarioStart();
            bandeauWithThreads3.scenarioStart();

            try {
                bandeauWithThreads1.getThread().join();
                bandeauWithThreads2.getThread().join();
                bandeauWithThreads3.getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Scenario makeScenario() {
        // On crée un scenario
        Scenario s = new Scenario();
        // On lui ajoute des effets
        s.addEffect(new RandomEffect("Le jeu du pendu", 700), 1);
         s.addEffect(new TeleType("Je m'affiche caractère par caractère", 100), 1);
        // s.addEffect(new Blink("Je clignote 10x", 100), 10);
        // s.addEffect(new Zoom("Je zoome", 50), 1);
        // s.addEffect(new FontEnumerator(10), 1);
        // s.addEffect(new Rainbow("Comme c'est joli !", 30), 1);
        s.addEffect(new Rotate("2 tours à droite", 180, 4000, true), 2);
        // s.addEffect(new Rotate("2 tours à gauche", 180, 4000, false), 2);
        return s;
    }
}