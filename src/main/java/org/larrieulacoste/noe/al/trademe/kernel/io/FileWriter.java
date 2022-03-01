package org.larrieulacoste.noe.al.trademe.kernel.io;

import org.larrieulacoste.noe.al.trademe.kernel.exception.FileException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileWriter implements Writer {
    private final String source;

    public FileWriter(String source) {
        this.source = Objects.requireNonNull(source);
    }


    @Override
    public void write(String data) {
        File file = new File(this.source);
        if (!file.exists()) {
            file.mkdirs();
        }

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(file, StandardCharsets.UTF_8, false)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }
}
