package dev.tiltrikt.tetravex.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TokenResponse {

  @NotNull String token;

}
