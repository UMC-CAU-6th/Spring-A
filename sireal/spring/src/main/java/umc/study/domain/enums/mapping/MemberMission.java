<<<<<<< HEAD
package umc.study.domain.enums.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;
}
=======
package umc.study.domain.enums.mapping;

public class MemberMission {
}
>>>>>>> d889209aae336fd555a1ee3a5f1b5675adbd1059
