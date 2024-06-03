package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.AdminService.MissionCommandService;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import jakarta.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReveiwDTO request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @PostMapping("/{storeId}/mission")
    public ApiResponse<MissionResponseDTO.UserMissionResultDto> addUserMission(@RequestBody @Valid MissionRequestDTO.UserMissionDto request,
                                                                              @ExistStore @PathVariable(name = "storeId") Long storeId){
        MemberMission Mission = missionCommandService.addUserMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toMemberMissionResultDTO(Mission));
    }
}
