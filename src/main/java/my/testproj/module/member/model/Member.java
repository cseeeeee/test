package my.testproj.module.member.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Member {
    private Long id;
    private String nickname;
    private String email;
    private String emailHash;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
