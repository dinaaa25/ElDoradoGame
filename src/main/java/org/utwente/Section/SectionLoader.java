package org.utwente.Section;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.utwente.Tile.Tile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SectionLoader {
    public static List<Section> loadSections() {
        File directory = new File("./src/main/java/org/utwente/Section/");
        Pattern pattern = Pattern.compile("Section([A-Za-z]+)\\.json");
        FilenameFilter filter = (dir, name) -> name.matches("Section[A-Za-z]+\\.json");

        File[] files = directory.listFiles(filter);

        if (files == null) {
            System.out.println("No files found or an error occurred.");
            return List.of();
        }

        List<Section> sections = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (File file : files) {
            try {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.find()) {
                    List<Tile> sectionTiles = mapper.readValue(file, new TypeReference<List<Tile>>(){});
                    SectionType sectionType = SectionType.valueOf(matcher.group(1));
                    sections.add(new Section(sectionTiles, sectionType));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sections;
    }
}