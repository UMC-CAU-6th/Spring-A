package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.domain.Store;
import umc.study.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

public class MissionRequestDTO {
    @Getter
    public static class MissionDto{
        @NotBlank
        Integer reward;
        @NotNull
        LocalDate deadline;
        @NotNull
        String missionSpec;
        @NotNull
        Store store;
    }

    @Getter
    public static class UserMissionDto{
        @NotBlank
        Long missionId;
        @NotNull
        Store store;
    }
}
