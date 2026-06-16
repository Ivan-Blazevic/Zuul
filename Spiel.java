/**
 *  Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul".
 *  "Die Welt von Zuul" ist ein sehr einfaches, textbasiertes
 *  Adventure-Game. Ein Spieler kann sich in einer Umgebung bewegen,
 *  mehr nicht. Das Spiel sollte auf jeden Fall ausgebaut werden,
 *  damit es interessanter wird!
 * 
 *  Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und
 *  an ihr die Methode "spielen" aufgerufen werden.
 * 
 *  Diese Instanz erzeugt und initialisiert alle anderen Objekte
 *  der Anwendung: Sie legt alle Räume und einen Parser an und
 *  startet das Spiel. Sie wertet auch die Befehle aus, die der
 *  Parser liefert, und sorgt für ihre Ausführung.
 * 
 * @author  Michael Kölling und David J. Barnes
 * @version 2016.02.29
 */

public class Spiel 
{
    private Parser parser;
    private Raum aktuellerRaum;
        
    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        raeumeAnlegen();
        parser = new Parser();
    }

    /**
     * Erzeuge alle Räume und verbinde ihre Ausgänge miteinander.
     */
    private void raeumeAnlegen()
    {

        Raum draussen, flur1, flur2, klassenraum, klasse1, klasse2, klasse3, klasse4,
             toiletteJ, toiletteM, schrank1, schrank2, cafeteria;
      
        // die Räume erzeugen

        draussen = new Raum("vor dem Haupteingang der Schule");
        flur1 = new Raum("in der Flur beim Haupteingang");
        flur2 = new Raum("in der anderen Flur");
        klassenraum = new Raum("in der Klassenraum");
        klasse1 = new Raum("in der 1. Klasse");
        klasse2 = new Raum("in der 2. Klasse");
        klasse3 = new Raum("in der 3. Klasse");
        klasse4 = new Raum("in der 4. Klasse");
        toiletteJ = new Raum("in der Jungen Toilette");
        toiletteM = new Raum("In der Madchen Toilette");
        schrank1 = new Raum("in der 1. Schrank");
        schrank2 = new Raum("in der 2. Schrank");
        cafeteria = new Raum("Cafeteria");

        
        // die Ausgänge initialisieren

        // ricthtung: north, east, south, west

        //draussen.setzeAusgaenge(null, null, flur1, null);
        draussen.setzeAusgaenge("south", flur1);

        //flur1.setzeAusgaenge(draussen, toiletteM, flur2, toiletteJ);
        flur1.setzeAusgaenge("north", draussen);
        flur1.setzeAusgaenge("east", toiletteM);
        flur1.setzeAusgaenge("south", flur2);
        flur1.setzeAusgaenge("west", toiletteJ);

        //flur2.setzeAusgaenge(flur1, klasse1, klasse3, klasse2);
        flur2.setzeAusgaenge("north", flur1);
        flur2.setzeAusgaenge("east", klasse1);
        flur2.setzeAusgaenge("south", klasse3);
        flur2.setzeAusgaenge("west", klasse2);

        // ricthtung: north, east, south, west

        //toiletteJ.setzeAusgaenge(null, flur1, null, null);
        toiletteJ.setzeAusgaenge("east", flur1);

        //toiletteM.setzeAusgaenge(null, null, null, flur2);
        toiletteM.setzeAusgaenge("west", flur2);

        //schrank1.setzeAusgaenge(null, schrank2, null, klasse1);
        schrank1.setzeAusgaenge("east", schrank2);
        schrank1.setzeAusgaenge("west", klasse1);

        //schrank2.setzeAusgaenge(null, klassenraum, null, schrank1);
        schrank2.setzeAusgaenge("east", klassenraum);
        schrank2.setzeAusgaenge("west", schrank1);

        // ricthtung: north, east, south, west

        //klasse1.setzeAusgaenge(null, schrank1, null, flur2);
        klasse1.setzeAusgaenge("east", schrank1);
        klasse1.setzeAusgaenge("west", flur2);

        //klasse2.setzeAusgaenge(null, flur2, klasse4, null);
        klasse2.setzeAusgaenge("east", flur2);
        klasse2.setzeAusgaenge("south", klasse4);

        //klasse3.setzeAusgaenge(flur2, null, null, klasse4);
        klasse3.setzeAusgaenge("north", flur2);
        klasse3.setzeAusgaenge("west", klasse4);

        // ricthtung: north, east, south, west

        //klasse4.setzeAusgaenge(klasse2, klasse3, null, null);
        klasse4.setzeAusgaenge("north", klasse2);
        klasse4.setzeAusgaenge("east", klasse3);

        //klassenraum.setzeAusgaenge(null, cafeteria, null, schrank2);
        klassenraum.setzeAusgaenge("east", cafeteria);
        klassenraum.setzeAusgaenge("west", schrank2);

        //cafeteria.setzeAusgaenge(null, draussen, null, klassenraum);
        cafeteria.setzeAusgaenge("east", draussen);
        cafeteria.setzeAusgaenge("west", klassenraum);


        aktuellerRaum = draussen;  // das Spiel startet draussen
    }

    /**
     * Die Hauptmethode zum Spielen. Läuft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und führen sie aus, bis das Spiel beendet wird.
                
        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Danke für dieses Spiel. Auf Wiedersehen.");
    }

    /**
     * Einen Begrüßungstext für den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zu Zuul!");
        System.out.println("Zuul ist ein neues, unglaublich langweiliges Spiel.");
        System.out.println("Tippen Sie 'help', wenn Sie Hilfe brauchen.");
        System.out.println();
        rauminfoAusgeben();
    }

    /**
     * Verarbeite einen gegebenen Befehl (führe ihn aus).
     * @param befehl   der zu verarbeitende Befehl.
     * @return true    wenn der Befehl das Spiel beendet, false sonst
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        if(befehl.istUnbekannt()) {
            System.out.println("Ich weiss nicht, was Sie meinen ...");
            return false;
        }
        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help")) {
            hilfstextAusgeben();
        }
        else if (befehlswort.equals("go")) {
            wechsleRaum(befehl);
        }
        else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        
        return moechteBeenden;
    }

    // Implementierung der Benutzerbefehle:

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlswörter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Sie haben sich verlaufen. Sie sind allein.");
        System.out.println("Sie irren auf dem Unigelände herum.");
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
        System.out.println("   go quit help");
    }

    /**
     * Versuche, in eine Richtung zu gehen. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    private void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin möchten Sie gehen?");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen, den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);

        if (naechsterRaum == null) {
            System.out.println("Dort ist keine Tür!");
        }
        else {
            aktuellerRaum = naechsterRaum;
            rauminfoAusgeben();
        }
    }

    private void rauminfoAusgeben() {
        System.out.println("Sie sind " + aktuellerRaum.gibBeschreibung());
        System.out.println(aktuellerRaum.gibAusgaengeAlsString());

        System.out.println();
    }

    /**
     * "quit" wurde eingegeben. Überprüfe den Rest des Befehls,
     * ob das Spiel wirklich beendet werden soll.
     * @return true  wenn der Befehl das Spiel beendet, false sonst
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        else {
            return true;  // Das Spiel soll beendet werden.
        }
    }
}
