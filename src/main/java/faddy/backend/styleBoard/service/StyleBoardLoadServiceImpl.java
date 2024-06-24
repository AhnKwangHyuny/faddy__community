package faddy.backend.styleBoard.service;

import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoard.repository.StyleBoardJpaRepository;
import faddy.backend.styleBoard.service.useCase.StyleBoardLoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StyleBoardLoadServiceImpl implements StyleBoardLoadService {

    private final StyleBoardJpaRepository styleBoardRepository;

    @Override
    public List<StyleBoard> getAll() {
        return styleBoardRepository.findAll();
    }
}
