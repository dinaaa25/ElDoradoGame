package org.utwente.Section;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Board.SectionDirectionType;

@Getter
@Setter
public class SectionWithRotationPositionSectionDirection {
    private SectionType sectionType;
    private int rotation;
    private int placement;
    private SectionDirectionType.SectionDirection sectionDirection;

    public SectionWithRotationPositionSectionDirection(SectionType sectionType, int rotation, int placement, SectionDirectionType.SectionDirection sectionDirection) {
        this.sectionType = sectionType;
        this.rotation = rotation;
        this.placement = placement;
        this.sectionDirection = sectionDirection;
    }

    @Override
    public String toString() {
        return "SectionWithRotationPositionSectionDirection{" +
                "sectionType=" + sectionType +
                ", rotation=" + rotation +
                ", placement=" + placement +
                ", sectionDirection=" + sectionDirection +
                '}';
    }
}
