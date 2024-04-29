package faddy.backend.snap.service;

import faddy.backend.global.Utils.EncryptionUtils;
import faddy.backend.global.exception.SnapException;
import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.domain.dto.response.SnapResponseDto;
import faddy.backend.snap.repository.CustomSnapRepository;
import faddy.backend.snap.repository.SnapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SnapService {

    private final SnapRepository snapRepository;
    private final CustomSnapRepository queryDslRepository;

    /**
     * 활성화된 Snap을 ID로 조회
     * @param snapId 조회할 Snap의 ID
     * @return 조회된 Snap object
     * @throws SnapException 활성 상태의 Snap을 찾을 수 없는 경우 예외 발생
     */
    public Snap getActiveSnapById(Long snapId) {
        return snapRepository.findByIdAndDeletedAtIsNull(snapId)
            .orElseThrow(() ->
                    new SnapException(
                        HttpStatus.BAD_REQUEST.value() , "찾으시는 스냅이 존재하지 않습니다. {id : " + snapId + "}"
                )
        );
    }


    /**
     * snap detail page에 필요한 data 클라이언트로 반환
     * @param snapId 조회할 Snap의 ID
     * @return 조회된 Snap with user , profile , hashTags , images
     */
    @Transactional
    public SnapResponseDto getSnapResponseDtoWithSnapProjection(String snapId) throws Exception {

        // snapId 복호화
        Long rawId = EncryptionUtils.decryptEntityId(snapId);

        //해당 snapId를 가진 entity가 db에 저장되어있는지 확인
        validateSnapExists(rawId , snapId);

        //snapProjection 조회 by snapId
        Optional<Snap> snapOpt = snapRepository.findById(rawId);

        System.out.println("snapOpt.get() = " + snapOpt.get());
        
        //SnapProjection 생성 성공 시 responseDto로 변환
        return new SnapResponseDto();
    };

    private void validateSnapExists(Long rawId, String encryptedSnapId) {

        boolean exists = snapRepository.existsByIdAndDeletedAtIsNull(rawId);
        if (!exists) {

            log.warn("raw snap ID : " + rawId);

            throw new SnapException(
                    HttpStatus.BAD_REQUEST.value(), "찾으시는 스냅이 존재하지 않습니다. {id : " + encryptedSnapId + "}"
            );
        }
    }

}

