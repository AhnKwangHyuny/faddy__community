package faddy.backend.styleBoard.service;

import faddy.backend.hashTags.repository.HashTagRepository;
import faddy.backend.log.exception.ExceptionLogger;
import faddy.backend.styleBoard.domain.Category;
import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoard.dto.response.InteractionCountDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardResponseDTO;
import faddy.backend.styleBoard.dto.response.UserProfileDTO;
import faddy.backend.styleBoard.repository.StyleBoardJpaRepository;
import faddy.backend.styleBoard.service.useCase.InteractionCountService;
import faddy.backend.styleBoard.service.useCase.StyleBoardLoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StyleBoardLoadServiceImpl implements StyleBoardLoadService {

    private final StyleBoardJpaRepository styleBoardRepository;

    private final HashTagRepository hashTagRepository;

    private final InteractionCountService interactionCountService;

    @Override
    public List<StyleBoard> getAll() {
        return styleBoardRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StyleBoardResponseDTO> getFilteredStyleBoards(String category, String sort, List<String> tags, int page, int size) {

        //파라미터 전부 조회
        log.info("category : {}, sort : {}, tags : {}, page : {}, size : {}", category, sort, tags, page, size);

        //페이징 정보 객체 생성
        PageRequest pageRequest = PageRequest.of(page , size);

        Category categoryData = Category.fromUrlValue(category);

        //filtered styleBoard 조회
        Page<StyleBoard> styleBoards;

        try{

            if (tags != null && !tags.isEmpty() && categoryData != null) {
                styleBoards = styleBoardRepository.findByCategoryAndTagsIn(categoryData, tags , tags.size(), pageRequest);
            } else if (tags != null && !tags.isEmpty()) {
                styleBoards = styleBoardRepository.findByTagsIn(tags, pageRequest);
            } else if (categoryData != null) {
                styleBoards = styleBoardRepository.findByCategory(categoryData, pageRequest);
            } else {
                styleBoards = styleBoardRepository.findAllStyleBoardsSortedByCreationDateDesc(pageRequest);
            }

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLogger.logException(e);
            throw e;
        }
        log.info("styleBoards : {}", styleBoards.getContent().size());

        return styleBoards.stream()
                .map(styleBoard -> {
                    InteractionCountDTO interactionCountDTO = interactionCountService.getInteractionCount(styleBoard.getId());

                    return toStyleBoardResponseDTO(styleBoard, interactionCountDTO);

                })
                .collect(Collectors.toList());
    }

    private Sort getSort(String sort) {
        switch (sort.toLowerCase()) {
            case "views":
                return Sort.by(Sort.Direction.DESC, "views");
            default:
                return null;
        }
    }

    private StyleBoardResponseDTO toStyleBoardResponseDTO(StyleBoard styleBoard, InteractionCountDTO interactionCountDTO) {
        return StyleBoardResponseDTO.builder()
                .boardId(styleBoard.getId())
                .title(styleBoard.getTitle())
                .createdAt(styleBoard.getCreated_at())
                .userProfileDTO(UserProfileDTO.from(styleBoard.getAuthor()))
                .interactionCountDTO(interactionCountDTO)
                .category(styleBoard.getCategory())
                .build();
    }


}
