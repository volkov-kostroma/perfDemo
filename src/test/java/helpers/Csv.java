package helpers;

import models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Csv {
    public static List<User> ReadUsers(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Пропускаем заголовок
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       return users;
    }
}
