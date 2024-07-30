package com.igriss.AkkSell.dtos;

import com.igriss.AkkSell.game_type.GameType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoPostDTO {
    private List<MultipartFile> photoFiles;
    private String video;
    private String title;
    private String description;
    private BigDecimal price;
    private GameType gameType;
    private Long userId;
}
