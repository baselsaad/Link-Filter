import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
/**
 * Die Klasse LinkFilter filtert die gegebenen HTML Code und gibt die gelesenen Website-Links, alle gelesenen Code Zeilen,
 * Anzahl der Zeichen und Anzahl die gefilterten Links aus.
 * 
 * @author (Basel) , (Anas)
 * @version 13.12.2021
 */
public class LinkFilter
{
    private static final String LEEREN_ZEILE = "";
    
    public static void main (String [] args){
        start();
    }
    
    public static void start (){
        System.out.println(fileReader (fileFilter ()));
    }
    
    /**
     * HTML datei wird gefiltert und alle Links und Titeln werden in Array gespeichert, die ungueltigen Zeilen werden als leeren Zeilen gespeichert
     */
    public static String[] fileFilter (){
	final String regex = "<a.* href=\"([^\"]*)\".*?>(.*)</a>";
        Pattern pattern = Pattern.compile(regex);
        Scanner htmlLeser = new Scanner (System.in);
        ArrayList<String> codeList  = new ArrayList<String> ();
         
        while (htmlLeser.hasNext()){
            String htmlCode = htmlLeser.nextLine();
            Matcher matcher = pattern.matcher(htmlCode);
            String title = "";
            String link = "";
            
            if (matcher.find()){
              link = matcher.group(1); //gruppe eins fuer Links
	      title = matcher.group(2); //gruppe zwei fuer Titel
            }
            
            if (title.isEmpty() || link.isEmpty()){
                codeList.add(LEEREN_ZEILE);
            }else {
                String zeilenText = "\nTitle: "+title+", Link: "+link+", Anzahl Zeichen: "+link.length()+"\n";
                codeList.add(zeilenText);
            }
            
        }
        
        String [] tempArray = codeList.toArray(new String [0]);
        return tempArray;
    }
    
    /**
     * Array wird gelesen und die ungueltigen Zeilen ignoriert
     */
    public static String fileReader(String[] datei){
        StringBuilder htmlDatei = new StringBuilder();
        int zeilenAnzahl = 0;
        for (int i = 0;i < datei.length;i++){
            if (!datei[i].isEmpty()){
                htmlDatei.append(datei[i]);
                zeilenAnzahl++;
            }
        }
        htmlDatei.append("\n"+zeilenAnzahl+" Links wurden in " +datei.length+" Zeilen gefunden\n");
        
        return String.valueOf(htmlDatei);
    }
   
}
