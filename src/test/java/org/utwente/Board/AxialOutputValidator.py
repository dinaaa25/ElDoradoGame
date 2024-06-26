import unittest

# Tool that can be used to check if the math in java code is correcta, could also be addapted to be used in the java
# code itself, or generate the test cases for the java code
class SectionWithRotationPositionSectionDirection:
    def __init__(self, section_direction, rotation=None, placement=None):
        self.section_direction = section_direction
        self.rotation = rotation
        self.placement = placement

    def getSectionDirection(self):
        return self.section_direction

    def getRotation(self):
        return self.rotation

    def getPlacement(self):
        return self.placement

class CoordinateBounds:
    def __init__(self, minQ, maxQ, minR, maxR):
        self._minQ = minQ
        self._maxQ = maxQ
        self._minR = minR
        self._maxR = maxR

    @property
    def minQ(self):
        return self._minQ

    @property
    def maxQ(self):
        return self._maxQ

    @property
    def minR(self):
        return self._minR

    @property
    def maxR(self):
        return self._maxR

class AxialTranslation:
    def __init__(self, q, r):
        self.q = q
        self.r = r

    def __eq__(self, other):
        return self.q == other.q and self.r == other.r

    def __repr__(self):
        return f"AxialTranslation(q={self.q}, r={self.r})"

class SectionDirectionType:
    class PointyTopSectionDirection:
        PT_NORTH = 'PT_NORTH'
        PT_NORTHEAST = 'PT_NORTHEAST'
        PT_SOUTHEAST = 'PT_SOUTHEAST'
        PT_SOUTH = 'PT_SOUTH'
        PT_SOUTHWEST = 'PT_SOUTHWEST'
        PT_NORTHWEST = 'PT_NORTHWEST'

    @staticmethod
    def toPointyTopSectionDirection(direction):
        return direction

def getTranslationElDorado(sectionWithData, coordinateBounds):
    sectionDirection = SectionDirectionType.toPointyTopSectionDirection(sectionWithData.getSectionDirection())
    rotation = sectionWithData.getRotation()

    if sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST and rotation == 0:
        translationQ = coordinateBounds.maxQ + 1
        translationR = coordinateBounds.minR - 1
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST and rotation == 1:
        translationQ = coordinateBounds.maxQ + 1
        translationR = (coordinateBounds.minQ + coordinateBounds.maxQ) // 2
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_SOUTH and rotation == 2:
        translationQ = (coordinateBounds.minQ + coordinateBounds.maxQ) // 2
        translationR = coordinateBounds.maxQ + 1
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST and rotation == 3:
        translationQ = coordinateBounds.minQ - 1
        translationR = coordinateBounds.maxR + 1
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST and rotation == 4:
        translationQ = coordinateBounds.minQ - 1
        translationR = (coordinateBounds.minR + coordinateBounds.maxR) // 2
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_NORTH and rotation == 5:
        translationQ = (coordinateBounds.minQ + coordinateBounds.maxQ) // 2
        translationR = coordinateBounds.minR - 1
        return AxialTranslation(translationQ, translationR)
    else:
        return AxialTranslation(0, 0) 

def getTranslationNormalSection(sectionWithData, coordinateBounds):
    sectionDirection = SectionDirectionType.toPointyTopSectionDirection(sectionWithData.getSectionDirection())
    placement = sectionWithData.getPlacement()

    if sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST:
        translationQ = coordinateBounds.maxQ + 1 + 3
        translationR = coordinateBounds.minR - 1 + placement
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST:
        translationQ = coordinateBounds.maxQ + 1 - placement
        translationR = coordinateBounds.maxR + placement
        if placement == -1:
            translationQ -= 2
            translationR += 1
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_SOUTH:
        translationQ = coordinateBounds.minQ - placement
        translationR = coordinateBounds.maxR + 3 + 1
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST:
        translationQ = coordinateBounds.minQ - 3 - 1
        translationR = coordinateBounds.maxR + 1 - placement
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST:
        translationQ = coordinateBounds.minQ - placement
        translationR = coordinateBounds.minR - 1 + placement
        if placement == -1:
            translationQ -= 1
            translationR += 2
        return AxialTranslation(translationQ, translationR)
    elif sectionDirection == SectionDirectionType.PointyTopSectionDirection.PT_NORTH:
        translationQ = coordinateBounds.maxQ + placement
        translationR = coordinateBounds.minR - 3 - 1
        return AxialTranslation(translationQ, translationR)

class TestGetTranslationMethods(unittest.TestCase):
    def setUp(self):
        self.coordinateBounds = CoordinateBounds(minQ=0, maxQ=10, minR=0, maxR=10)

    def test_getTranslationElDorado(self):
        test_data = [
            (SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST, 0, AxialTranslation(11, -1)),
            (SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST, 1, AxialTranslation(11, 5)),
            (SectionDirectionType.PointyTopSectionDirection.PT_SOUTH, 2, AxialTranslation(5, 11)),
            (SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST, 3, AxialTranslation(-1, 11)),
            (SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST, 4, AxialTranslation(-1, 5)),
            (SectionDirectionType.PointyTopSectionDirection.PT_NORTH, 5, AxialTranslation(5, -1)),
            (SectionDirectionType.PointyTopSectionDirection.PT_NORTH, 6, AxialTranslation(0, 0)), # default case
        ]

        for sectionDirection, rotation, expected in test_data:
            with self.subTest(sectionDirection=sectionDirection, rotation=rotation, expected=expected):
                sectionWithData = SectionWithRotationPositionSectionDirection(sectionDirection, rotation)
                result = getTranslationElDorado(sectionWithData, self.coordinateBounds)
                self.assertEqual(result, expected)

    def test_getTranslationNormalSection(self):
        test_data = [
            (SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST, 3, AxialTranslation(14, 2)),
            (SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST, 2, AxialTranslation(9, 12)),
            # (SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST, -1, AxialTranslation(7, 12)), # special case
            (SectionDirectionType.PointyTopSectionDirection.PT_SOUTH, 1, AxialTranslation(-1, 14)),
            (SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST, 2, AxialTranslation(-4, 9)),
            (SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST, 3, AxialTranslation(-3, 2)),
            # (SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST, -1, AxialTranslation(-4, 3)), # special case
            (SectionDirectionType.PointyTopSectionDirection.PT_NORTH, 4, AxialTranslation(14, -4)),
        ]

        for sectionDirection, placement, expected in test_data:
            with self.subTest(sectionDirection=sectionDirection, placement=placement, expected=expected):
                sectionWithData = SectionWithRotationPositionSectionDirection(sectionDirection, placement=placement)
                result = getTranslationNormalSection(sectionWithData, self.coordinateBounds)
                self.assertEqual(result, expected)

if __name__ == "__main__":
    unittest.main()



