package org.larrieulacoste.noe.al.trademe.kernel.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import org.larrieulacoste.noe.al.trademe.kernel.exception.FileException;

public class OtherFileReader implements Reader {
    private final Path source;

    public OtherFileReader(String source) {
        this.source = Paths.get(Objects.requireNonNull(source));
    }

    @Override
    public String read() {
        try {
            return Files.readString(source);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }
}
