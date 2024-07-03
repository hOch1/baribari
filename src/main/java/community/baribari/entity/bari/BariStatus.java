package community.baribari.entity.bari;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BariStatus {

    RECRUITING("모집중"), COMPLETED("모집완료");

    private final String name;
}
