package community.baribari.dto.search;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    @NotBlank(message = "검색어를 입력해주세요")
    @Size(min = 2, message = "검색어는 2자 이상 입력해주세요.")
    private String keyword;

    private SearchType searchType;
}
