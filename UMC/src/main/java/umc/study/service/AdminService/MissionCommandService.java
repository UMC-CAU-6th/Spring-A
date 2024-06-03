package umc.study.service.AdminService;

import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.StoreRequestDTO;

public interface MissionCommandService {

    Mission addMission(MissionRequestDTO.MissionDto request);
    MemberMission addUserMission(Long storeId, MissionRequestDTO.UserMissionDto request);
}
