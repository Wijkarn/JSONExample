import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {

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


    }
}