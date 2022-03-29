import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        for (Object employee : list) {
            System.out.println(employee);
        }
    }

    private static String readString(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            return sb.toString();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            Object obj = parser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object employeeObj : jsonArray) {
                Employee employee = gson.fromJson(employeeObj.toString(), Employee.class);
                list.add(employee);
            }

        } catch(ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
