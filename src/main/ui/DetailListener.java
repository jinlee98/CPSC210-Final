package ui;

import java.util.EventListener;

public interface DetailListener extends EventListener {
    void detailEventOccurred(DetailEvent event);
}
