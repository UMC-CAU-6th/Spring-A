package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.service.AdminService.MissionCommandService;
import umc.study.service.AdminService.StoreCommandService;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.web.dto.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/admin")
public class AdminRestController {

    private final StoreCommandService storeCommandService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/stores")
    public ApiResponse<StoreResponseDTO.CreateStoreResultDTO> add(@RequestBody @Valid StoreRequestDTO.AddDTO request){
        Store store = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreResultDTO(store));
    }

    @PostMapping("/mission")
    public ApiResponse<MissionResponseDTO.MissionResultDto> add(@RequestBody @Valid MissionRequestDTO.MissionDto request){
        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));
    }
}
