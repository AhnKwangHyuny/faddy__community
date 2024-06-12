package faddy.backend.hashTags.repository.custom;

import faddy.backend.hashTags.domain.HashTag;

import java.util.List;

public interface CustomHashTagRepository {
    List<HashTag> saveAll(List<HashTag> hashTags);
}