import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class Raum
{
    private String beschreibung;
    private HashMap<String, Raum> ausgaenge;
    private HashMap<String, Gegenstand> geg;

    public Raum(String beschreibung)
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap<>();
        this.geg = new HashMap<>();
    }

    public void setzeAusgaenge(String richtung, Raum nachbar)
    {
        ausgaenge.put(richtung,nachbar);
    }

    public String gibBeschreibung()
    {
        return beschreibung;
    }

    public Raum gibAusgang(String richtung){
        return ausgaenge.get(richtung);
    }

    public String gibAusgaengeAlsString(){
        String str= "Ausgänge:" ;
        Iterator <String> it= ausgaenge.keySet().iterator();
        while (it.hasNext()){
            String richtung = it.next();
            str+= " " + richtung;
        }
        return str;
    }

    public String gibLangeBeschreibung()
    {
        Iterator<Map.Entry<String, Gegenstand>> it = geg.entrySet().iterator();
        String beschr = "Sie sind " + this.beschreibung + ".\n" + gibAusgaengeAlsString();
        while (it.hasNext()){
            Map.Entry<String, Gegenstand> entry = it.next();
            beschr += "\nGegenstand: " + entry.getValue().gibBeschreibung()
                    + " (Gewicht: " + entry.getValue().gibGewicht() + "g) \n"
                    + " [take: " + entry.getKey() + "]";
        }
        return beschr;
    }

    public void gegenstandAblegen(Gegenstand g, String name){
        if (geg.containsKey(name) == true ){
            System.out.println("Der Gegenstand ist schon  in diesem Raum");
        }
        else{ geg.put(name, g); }
    }

    public Gegenstand gegenstandAufheben(String name)
    {
        return geg.remove(name);
    }
}