package org.larrieulacoste.noe.al.trademe.kernel.io;

import org.larrieulacoste.noe.al.trademe.kernel.exception.FileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileReader implements Reader {
    private final String source;

    public FileReader(String source) {
        this.source = Objects.requireNonNull(source);
    }

    @Override
    public String read() {
        File file = new File(this.source);
        if (!file.exists()) {
            return null;
        }
        try (java.io.FileReader fileReader = new java.io.FileReader(file, StandardCharsets.UTF_8)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            bufferedReader.close();
            return builder.toString();
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }
}
