package snaked.model;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ExampleLombokClass {
    private final @Getter
    @EqualsAndHashCode.Include int id;
    private @Getter
    @Setter String name;
}
