package org.larrieulacoste.noe.al.trademe.kernel.event;

import org.larrieulacoste.noe.al.trademe.kernel.logger.Logger;

public class TestLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void error(String message) {
        System.out.println(message);
    }
}
