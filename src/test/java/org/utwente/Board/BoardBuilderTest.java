package org.utwente.Board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.utwente.Section.Section;
import org.utwente.Section.SectionLoader;
import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;
import org.utwente.Tile.Tile;

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
        assertEquals(startingSection, builder.getSections().get(0), "First section should be starting section specified");
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

    @ParameterizedTest
    @EnumSource(Path.class)
    void testPathSelection(Path selectedPath) {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Board board = builder.selectPath(selectedPath).buildPath().addBlockades().build();

        List<SectionType> expectedSectionTypes = Board.BoardBuilder.paths.get(selectedPath).stream()
                .map(SectionWithRotationPositionSectionDirection::getSectionType)
                .toList();;

        List<Section> sections = board.getSections();
        assertEquals(expectedSectionTypes.size(), sections.size(), "Number of sections should match");

        for (int i = 0; i < expectedSectionTypes.size(); i++) {
            assertEquals(expectedSectionTypes.get(i), sections.get(i).getSectionType(), "Section type should match at index " + i);
        }
    }

    @Test
    void testInvalidPathSelection() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        assertThrows(IllegalArgumentException.class, () -> {builder.selectPath(null);});
    }

    @Test
    void testBuildingEmptyBoard() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        assertThrows(AssertionError.class, builder::build);
    }

    @Test
    void testGetElDoradoTile() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Board board = builder.selectPath(Path.HillsOfGold).buildPath().addBlockades().build();
        Tile elDoradoTile = board.getElDoradoTile();
        assertTrue(elDoradoTile.isEndTile());
    }
}
