package org.utwente.Board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Section.Section;
import org.utwente.Section.SectionLoader;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BoardBuilderTest {
    List<Section> sections;

    @BeforeEach
    void setUp() {
        sections = SectionLoader.loadSections();
    }

    @Test
    void testInitialSectionAddition() {
        assertThrows(IllegalArgumentException.class, () -> {
            Board.BoardBuilder builder = new Board.BoardBuilder();
            Section nonStartingSection = sections.stream()
                    .filter(section -> !section.isStartingSection())
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("No non-starting section found"));

            builder.addInitialSection(nonStartingSection);
        }, "Adding a non-starting section with addInitialSection is not allowed");

        Board.BoardBuilder builder = new Board.BoardBuilder();
        Section startingSection = sections.stream()
                .filter(Section::isStartingSection)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No starting section found"));

        builder = builder.addInitialSection(startingSection);
        assertEquals(startingSection, builder.build().getSections().get(0), "First section should be starting section specified");
    }

    @Test
    void addingTwoInitialSectionsNotPossible() {
        assertThrows(IllegalStateException.class, () -> {
            Board.BoardBuilder builder = new Board.BoardBuilder();
            Section startingSection = sections.stream()
                    .filter(Section::isStartingSection)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("No starting section found"));
            builder.addInitialSection(startingSection).addInitialSection(startingSection);
        });
    }
}
