package org.utwente.Tile.view;

import org.utwente.Board.SectionDirectionType;
import java.util.*;

public class EdgeSectionDirection {
  public static List<Integer> getEdgesForSectionDirection(SectionDirectionType.SectionDirection sectionDirection) {
    if (sectionDirection instanceof SectionDirectionType.FlatTopSectionDirection flatTopSectionDirection) {
      return switch (flatTopSectionDirection) {
        case FT_NORTHEAST -> List.of(4, 5);
        case FT_EAST -> List.of(5, 0);
        case FT_SOUTHEAST -> List.of(0, 1);
        case FT_SOUTHWEST -> List.of(1, 2);
        case FT_WEST -> List.of(2, 3);
        case FT_NORTHWEST -> List.of(3, 4);
      };
    } else if (sectionDirection instanceof SectionDirectionType.PointyTopSectionDirection pointyTopSectionDirection) {
      return switch (pointyTopSectionDirection) {
        case PT_NORTH -> List.of(3, 4);
        case PT_NORTHEAST -> List.of(4, 5);
        case PT_SOUTHEAST -> List.of(5, 0);
        case PT_SOUTH -> List.of(0, 1);
        case PT_SOUTHWEST -> List.of(1, 2);
        case PT_NORTHWEST -> List.of(2, 3);
      };
    }
    return List.of(0, 0);
  }
}
