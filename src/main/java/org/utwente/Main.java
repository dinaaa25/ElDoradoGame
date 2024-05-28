package org.utwente;

import org.utwente.Section.Section;
import org.utwente.Section.SectionLoader;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketCli;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void bootstrap() {
        Market market = new Market();
        MarketCli view = new MarketCli();
        MarketController controller = new MarketController(view, market);
        view.run();
    }

    public static void main(String[] args) {
        // bootstrap();
        List<Section> sections = SectionLoader.loadSections();
        Optional<Section> section = sections.stream()
                .filter(s -> s.getSectionType() == SectionType.A)
                .findFirst();

        if (section.isPresent()) {
            Section foundSection = section.get();

            for (int r = -3; r <= 3; r++) {
                int leadingSpaces = Math.abs(r + 3);
                if (r < 0) {
                    leadingSpaces =  Math.abs(r + -3);
                }

                for (int s = 0; s < leadingSpaces; s++) {
                    System.out.print("   ");
                }

                int finalR = r;
                List<Tile> tiles = foundSection.getTiles().stream()
                        .filter(t -> t.getR() == finalR)
                        .toList();

                for (Tile tile : tiles) {
                    System.out.print("(" + tile.getQ() + ", " + tile.getR() + ") ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No section found with SectionType.A");
        }
    }
}