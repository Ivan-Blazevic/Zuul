
/**
 * Write a description of class v here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gegenstand
{
    private String beschreibung;
    private int gewicht;

    public Gegenstand(String beschreibung, int gewicht)
    {
        this.beschreibung = beschreibung;
        this.gewicht = gewicht;
    }
    public String gibBeschreibung()
    {
        return beschreibung;
    }
    public int gibGewicht()
    {
        return gewicht;
    }
}