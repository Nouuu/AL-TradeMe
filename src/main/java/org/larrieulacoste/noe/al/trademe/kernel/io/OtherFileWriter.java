package org.larrieulacoste.noe.al.trademe.kernel.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.larrieulacoste.noe.al.trademe.kernel.exception.FileException;

public class OtherFileWriter implements Writer {
    private final Path source;

    public OtherFileWriter(String source) {
        this.source = Paths.get(source);
    }

    @Override
    public void write(String data) {
        try {
            Files.write(source, data.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }
}
