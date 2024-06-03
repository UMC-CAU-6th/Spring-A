package umc.study.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    @Override
    @Transactional
    public Mission addMission(MissionRequestDTO.MissionDto request) {

        Mission newMission = MissionConverter.toMission(request);

        return missionRepository.save(newMission);
    }

    @Override
    @Transactional
    public MemberMission addUserMission(Long storeId, MissionRequestDTO.UserMissionDto request) {

        MemberMission newMemberMission = MissionConverter.toMemberMission(request);

        return memberMissionRepository.save(newMemberMission);
    }
}
