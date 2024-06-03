package umc.study.service.AdminService;

import umc.study.domain.Member;
import umc.study.domain.Store;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store addStore(StoreRequestDTO.AddDTO request);
}
