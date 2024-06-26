package uz.pdp.telegram.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import uz.pdp.telegram.model.user.TelegramUser;
import uz.pdp.telegram.state.user.State;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class TelegramUserSerializer {

    private static final String FILE_PATH = "src/main/resources/dataFiles/telegramuser.json";
    private final Gson gson;

    public TelegramUserSerializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(State.class, new StateAdapter());
        gson = gsonBuilder
                .setPrettyPrinting()
                .registerTypeAdapter(State.class, new StateAdapter())
                .create();
    }

    public void writeToFile(List<TelegramUser> users) {
        System.out.println("Keldi");
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException ignore) {
        }
    }

    public List<TelegramUser> readFromFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<List<TelegramUser>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception ignore) {}
        return null;
    }
}
