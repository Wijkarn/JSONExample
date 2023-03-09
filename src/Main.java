import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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

    static void fetchJsonFromAPI() throws IOException, ParseException {
        URL url = new URL("https://api.wheretheiss.at/v1/satellites/25544");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responsecode = conn.getResponseCode();

        if(conn.getResponseCode() == 200){
            System.out.println("Koppling lyckades");





            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            //Close the scanner
            scanner.close();

            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            //Get the required object from the above created object
            // JSONObject obj = (JSONObject) data_obj.get("name");
            // System.out.println(obj.get("TotalRecovered"));


            //Get the required data using its key
            System.out.println("Name: " + data_obj.get("name"));
            System.out.println("Id: " + data_obj.get("id"));
            System.out.println("Units: " + data_obj.get("units"));
            System.out.println("Velocity: " + data_obj.get("velocity"));
            System.out.println("Latitude: " + data_obj.get("latitude"));
            System.out.println("Longitude: " + data_obj.get("longitude"));

        }
        else{
            System.out.println("Koppling failed");
            throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
        }
    }

}