package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import project.entity.BreekEmoji;
import project.entity.BreeksLine;

public interface BreekEmojiRepository extends JpaRepository<BreekEmoji, Integer> {
    boolean existsByEmojiNum(Integer emojiNum);

    BreekEmoji findByEmojiNum(Integer emojiNum);
}
