package umc.study.web.dto;

import jakarta.persistence.*;
import lombok.Getter;
import umc.study.domain.Mission;
import umc.study.domain.Region;
import umc.study.domain.Review;
import umc.study.validation.annotation.ExistCategories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StoreRequestDTO {

    @Getter
    public static class ReveiwDTO{
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }

    @Getter
    public static class AddDTO{
        @NotBlank
        String name;
        @NotNull
        String address;
        @NotBlank
        Float score;
        @NotBlank
        Region region;
        @NotBlank
        List<Mission> missionList;
        @NotBlank
        List<Review> reviewList;
    }
}
