import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        System.out.println("Hello world!");
        System.out.println("Deez nutz");

        // Skapa ett JSON Object
        JSONObject jsonOb = new JSONObject();

        //Spara värden in JSON Object
        jsonOb.put("namn","Wijk");
        jsonOb.put("age","22");

        //Skriva ut värden
        System.out.println("Mitt namn är: " + jsonOb.get("namn"));
        System.out.println("Jag är " + jsonOb.get("age") + " år gammal.");
/*
        Object o = new JSONParser().parse(new FileReader("data.json"));
        JSONObject jsonData = (JSONObject) o;

        JSONObject person = (JSONObject) jsonData.get("p1");
        String nameP1= (String) person.get("name");
        String age= (String) person.get("age");

        JSONObject person2 = (JSONObject) jsonData.get("p2");
        String name2= (String) person2.get("name");

        System.out.println("P1 Name : " + nameP1);
        System.out.println("P1 Age : " + age);
        System.out.println("P2 Name : " + name2);
*/

    //fetchJsonFromFile();

    fetchJsonFromAPI();

    }

    static void fetchJsonFromFile() throws IOException, ParseException {

    JSONObject fetchData = (JSONObject) new JSONParser().parse(new FileReader("data.json"));

    JSONObject p1 = (JSONObject) fetchData.get("p1");
    JSONObject p2 = (JSONObject) fetchData.get("p2");

    String nameP1 = p1.get("name").toString(), nameP2 = p2.get("name").toString();
    int ageP1 = Integer.parseInt(p1.get("age").toString()), ageP2 = Integer.parseInt(p2.get("age").toString());

        System.out.println("Mitt namn är " + nameP1 + ". Jag är " + ageP1 + " år gammal" );
        System.out.println("Mitt namn är " + nameP2 + ". Jag är " + ageP2 + " år gammal" );

    }

    static void fetchJsonFromAPI() throws IOException {
        URL url = new URL("https://api.wheretheiss.at/v1/satellites/25544");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        if(conn.getResponseCode() == 200){
            System.out.println("Koppling lyckades");

        }
        else{
            System.out.println("Koppling failed");
        }
    }

}