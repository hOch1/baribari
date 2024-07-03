package community.baribari.entity.bari;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BariRegion {

    INCHEON("인천"), SEOUL("서울"), GYEONGGI("경기도"), GANGWON("강원도"),
    CHUNGNAM("충청남도"), CHUNGBUK("충청북도"), JEONABUN("전라북도"), JEONNAM("전라남도"),
    GYEONBUK("경상북도"), GYEONNAM("경상남도");

    private final String name;

    public static BariRegion fromDisplayName(String name) {
        for (BariRegion region : BariRegion.values()) {
            if (region.getName().equals(name)) {
                return region;
            }
        }
        return null;
    }

}
