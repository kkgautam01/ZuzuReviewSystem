package org.zuzureviewsystem.logging;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Log {
    private String content;
    private Long providerId;
    private String fileName;
}
