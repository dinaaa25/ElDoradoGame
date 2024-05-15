package org.utwente.CaveCoin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class CaveCoinLoader {
    public static List<CaveCoin> loadCoins() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File("src/main/java/org/utwente/CaveCoin/cavecoins.json"), new TypeReference<List<CaveCoin>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}