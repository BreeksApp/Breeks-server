package project.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.GeneratorType;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
public class BreekEmoji {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emoji_id")
    Integer id;

    @Column(nullable = false, unique = true)
    private Integer emojiNum;

    public BreekEmoji() {
    }

    public BreekEmoji(Integer emojiNum) {
        this.emojiNum = emojiNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmojiNum() {
        return emojiNum;
    }

    public void setEmojiNum(Integer emojiNum) {
        this.emojiNum = emojiNum;
    }
}
