package MultiThreadingJAVA;

//https://zestedesavoir.com/tutoriels/646/apprenez-a-programmer-en-java/558_java-et-la-programmation-evenementielle/2710_executer-des-taches-simultanement/
class ExecuterTachesSimultanement extends Thread{
    Thread t;

   ExecuterTachesSimultanement(String name){
        super(name);
        System.out.println("statut du thread " + name + " = " +this.getState());
        this.start();
        System.out.println("statut du thread " + name + " = " +this.getState());
    }

    ExecuterTachesSimultanement(String name, Thread t){
        super(name);
        this.t = t;
        System.out.println("statut du thread " + name + " = " +this.getState());
        this.start();
        System.out.println("statut du thread " + name + " = " +this.getState());
    }

    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("statut " + this.getName() + " = " +this.getState());
            if(t != null)
                System.out.println("statut de " + t.getName() + " pendant le thread " + this.getName() +" = " +t.getState());
        }
    }

    public void setThread(Thread t){
        this.t = t;
    }
    public static void main(String[] args) {
        ExecuterTachesSimultanement t = new ExecuterTachesSimultanement("A");
        ExecuterTachesSimultanement t2 = new ExecuterTachesSimultanement("  B",t);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("statut du thread " + t.getName() + " = " + t.getState());
        System.out.println("statut du thread " + t2.getName() + " = " +t2.getState());
    }
}
