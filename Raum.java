import java.util.HashMap;

/**
 * Diese Klasse modelliert Räume in der Welt von Zuul.
 * 
 * Diese Klasse ist Teil der Anwendung "Die Welt von Zuul".
 * "Die Welt von Zuul" ist ein sehr einfaches textbasiertes 
 * Adventure-Game.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Räumen über Ausgänge verbunden.
 * Mögliche Ausgänge liegen im Norden, Osten, Süden und Westen.
 * Für jede Richtung hält ein Raum eine Referenz auf den 
 * benachbarten Raum.
 * 
 * @author  Michael Kölling und David J. Barnes
 * @version 2016.02.29
 */
public class Raum 
{
    private String beschreibung;
    private HashMap<String, Raum> ausgaenge;

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausgänge. Eine Beschreibung hat die Form 
     * "in einer Küche" oder "auf einem Sportplatz".
     * @param beschreibung  die Beschreibung des Raums
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<>();
    }

    /**
     * Definiere die Ausgänge dieses Raums. Jede Richtung
     * führt entweder in einen anderen Raum oder ist 'null'
     * (kein Ausgang).
     * @param richtung  die Richtung, in der der Ausgang liegen soll.
     * @param nachbar der Raum, der ueber diesen Ausgang erreciht wird.
     */
    public void setzeAusgaenge(String richtung, Raum nachbar)
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * @return  die Beschreibung dieses Raums
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }

    public Raum gibAusgang(String richtung) {

        return ausgaenge.get(richtung);
    }

    public String gibAusgaengeAlsString() {
        String s = "Ausgänge:";

        if (nordausgang != null) {
            s += " north";
        }
        if (ostausgang != null) {
            s += " east";
        }
        if (suedausgang != null) {
            s += " south";
        }
        if (westausgang != null) {
            s += " west";
        }

        return s;
    }
}
