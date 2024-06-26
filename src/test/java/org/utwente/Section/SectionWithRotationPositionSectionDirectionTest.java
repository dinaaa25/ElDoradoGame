package org.utwente.Section;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.utwente.Board.SectionDirectionType;

class SectionWithRotationPositionSectionDirectionTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>
     * {@link SectionWithRotationPositionSectionDirection#SectionWithRotationPositionSectionDirection(SectionType, int, int, SectionDirectionType.SectionDirection)}
     *   <li>{@link SectionWithRotationPositionSectionDirection#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals(
                "SectionWithRotationPositionSectionDirection{sectionType=A, rotation=1, placement=1, sectionDirection"
                        + "=PT_NORTH}",
                (new SectionWithRotationPositionSectionDirection(SectionType.A, 1, 1,
                        SectionDirectionType.FlatTopSectionDirection
                                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST))).toString());
    }
}
