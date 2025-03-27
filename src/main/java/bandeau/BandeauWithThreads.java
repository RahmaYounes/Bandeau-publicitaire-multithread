package bandeau;

public class BandeauWithThreads {
    
    private final Bandeau bandeau;
    private final Scenario scenario;
    private final Thread thread;

    public BandeauWithThreads(Bandeau bandeau, Scenario scenario) {
        this.bandeau = bandeau;
        this.scenario = scenario;
        this.thread = new Thread(this::scenarioRun);
    }

    //Begin
    public void scenarioStart(){
        if(!thread.isAlive()){
            thread.start();
        }
    }

    //Run
    private void scenarioRun(){
        synchronized(bandeau){
            scenario.playOn(bandeau);
        }
    }

    //Getters
    public Thread getThread(){
        return this.thread;
    }
    
}
