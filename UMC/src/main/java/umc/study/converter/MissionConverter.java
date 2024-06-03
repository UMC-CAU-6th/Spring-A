package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.Gender;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissionConverter {

    public static MissionResponseDTO.MissionResultDto toMissionResultDTO(Mission mission){
        return MissionResponseDTO.MissionResultDto.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.MissionDto request){

        return Mission.builder()
                .store(request.getStore())
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .reward(request.getReward())
                .build();
    }

    public static MemberMission toMemberMission(MissionRequestDTO.UserMissionDto request){

        return MemberMission.builder()
                //.member()
                //.mission(request.getMissionId())
                //.status(0)
                .build();
    }

    public static MissionResponseDTO.UserMissionResultDto toMemberMissionResultDTO(MemberMission mission){
        return MissionResponseDTO.UserMissionResultDto.builder()
                .UserMissionId(mission.getId())
                //.UserId()
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
