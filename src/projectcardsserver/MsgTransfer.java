/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SimpleWorker, Token: Unknown
 *         MsgTransfer: Norrecito
 */
public class MsgTransfer implements Runnable {
  private static abstract class SimpleWorker implements Runnable {

        /*
         * A dolgozó szálkezelés irányításának megkezdése
         */
        private Thread runThread = null;
        private boolean running = false;

        public synchronized void start() {
            if (runThread != null && runThread.isAlive()) {
                throw new IllegalStateException("a dolgozó szál már fut.");
            }
            running = true;
            runThread = new Thread(this);
            runThread.start();
        }

        public synchronized void stop() {
            running = false;
            if (runThread != null) {
                runThread.interrupt();
            }
            runThread = null;
        }
        /*
         * A dolgozó szálkezelés irányításának vége
         */
       
        /*
         * A kiiratandó dolgok sorozata
         */
        private Vector<MsgTransfer.Token> queue = new Vector<MsgTransfer.Token>();

        public void submitToken(MsgTransfer.Token t) {
            queue.add(t);
        }
        /*
         * A kimeneti objektum csatorna
         * még a szál megkezdése elött be kell állítani
         */
        private ObjectOutputStream objectOutput;

        public ObjectOutputStream getObjectOutput() {
            return objectOutput;
        }

        public SimpleWorker() {
        }

        public SimpleWorker(ObjectOutputStream objectOutput) {
            this.objectOutput = objectOutput;
        }

        public void setObjectOutput(ObjectOutputStream objectOutput) {
            this.objectOutput = objectOutput;
        }

        protected abstract void onException(Exception ex);

        @Override
        public void run() {
            while (running) {
                if (queue.size() == 0) {
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException ex) {
                        // üres
                    }
                    continue;
                }
                MsgTransfer.Token aToken = queue.remove(0);
                try {
                    objectOutput.writeObject(aToken.outputMsg);
                    objectOutput.flush();
                }
                catch (IOException e) {
                    onException(e);
                }
                /*
                 * Értesíteni a szálat ami küldte ezt a "Token"-t, hogy befejeztük a vele kapcsolatos műveletetek
                 */
                synchronized(aToken) {
                    aToken.notify();
                }
            } // "while" ciklus végét jelző zárójel
        }

    }

 private static class Token {

        Object outputMsg;

        public Object getOutputMsg() {
            return outputMsg;
        }

        public void setOutputMsg(Object outputMsg) {
            this.outputMsg = outputMsg;
        }

    }  

/*
 * Az üzenetküldést intéző szál deklarálása
 */
 private MsgTransfer.SimpleWorker worker;
 
 /*
  * A kliens socket deklarálása
  */
 private Socket clientSocket;
 
 /*
  * Konstruktor
  */
 public MsgTransfer(Socket clientSocket){
    this.clientSocket = clientSocket; 
 }
 
 /*
  * Az üzenetküldést intéző szál leállítása
  */
 private void stopWorker(){
   if(worker != null){
      worker.stop();
      worker = null;
   }  
 }
  
  /*
   * Üzenet esetén
   */
  protected void onMessage(Object o){
      ;
  }
 
  /*
   * Induláskor
   */
  protected void onStart() {
        ;
  }
  
  /*
   * Üzenet küldését végző metódus
   */
  public void sendMessage(Serializable o) {
        sendMessage(o, true);
    }
  
  /*
   * Üzenet küldését végző metódus
   */
  public void sendMessage(Serializable o, boolean wait){
    if(worker != null){
        MsgTransfer.Token token = new MsgTransfer.Token();
        token.setOutputMsg(o);
        worker.submitToken(token);
        if(wait) synchronized(token){
                try {
                     token.wait();
                } catch (InterruptedException ex) {
                    /*
                     * Amennyiben kivétel keletkezik
                     */
                    Logger.getLogger(MsgTransfer.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }  
  }
  
 @Override
 public void run() {
    try{
    /*
     * Kimeneti csatorna létrehozása
     */
     worker = new MsgTransfer.SimpleWorker(new ObjectOutputStream(clientSocket.getOutputStream())) {

            @Override
            protected void onException(Exception ex) {
                /*
                 * Ha üzenetváltás közben kivétel keletkezik
                 */
                throw new UnsupportedOperationException("Not supported yet.");
            }
     };
     worker.start();
     onStart();
     /*
      * Bemeneti csatorna létrehozása
      */
     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
     /*
      * Amíg él a kapcsolat dolgozza fel az üzeneteket
      */
     while(!clientSocket.isClosed()) {
        onMessage(in.readObject());
     }
    }
    catch (Exception ex) {
       /*
        * Ha valamilyen kivétel keletkezik
        */
        System.out.println(ex);
    }
    finally{
        stopWorker(); //A dolgozó leállítása 
 }
 }
}


