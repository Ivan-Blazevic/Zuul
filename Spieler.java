import java.util.HashMap;
import java.util.Iterator;
/**
 * Write a description of class Spieler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Spieler
{
    private Raum aktuellerRaum;
    private HashMap <String, Gegenstand> gegenstaende;
    private int maxGewicht;

    public Spieler(Raum startraum)
    {
        aktuellerRaum = startraum;
        gegenstaende = new HashMap<>();
        maxGewicht = 1000;
    }

    public Raum gibAktuellenRaum()
    {
        return this.aktuellerRaum;
    }

    public void gehe(Raum neuerRaum)
    {
        this.aktuellerRaum = neuerRaum;
    }

    public boolean nehmen(Gegenstand g, String name)
    {
        if(gibGesamtgewicht() + g.gibGewicht() <= maxGewicht) {
            gegenstaende.put(name, g);
            return true;
        }
        return false;
    }

    public Gegenstand lege(String name)
    {
        return gegenstaende.remove(name);
    }

    public int gibGesamtgewicht()
    {
        Iterator<String> it = gegenstaende.keySet().iterator();
        int summe = 0;
        while (it.hasNext()){
            Gegenstand g = gegenstaende.get(it.next());
            summe += g.gibGewicht();
        }

        return summe;
    }

    public String gibStatus()
    {
        Iterator<Gegenstand> it= gegenstaende.values().iterator();
        String beschr = "";

        while (it.hasNext()){
            Gegenstand gs = it.next();

            beschr += "\nGegenstand: " + gs.gibBeschreibung()
                    + " (Gewicht: " + gs.gibGewicht() + "g)";
        }
        beschr += "\nGesamtgewicht: " + gibGesamtgewicht() + "g";
        return beschr;
    }

    // Aufgabe 8.33 Muffin essen => Gewicht erhoehen
    public void maxGewichtErhoehen(int wert)
    {
        this.maxGewicht += wert;
    }
}