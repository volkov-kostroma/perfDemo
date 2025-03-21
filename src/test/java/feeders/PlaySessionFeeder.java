package feeders;

import io.gatling.javaapi.core.*;
import providers.ListMapFromAPI;

import static io.gatling.javaapi.core.CoreDsl.*;

public class PlaySessionFeeder {

    public static ChainBuilder createSessionFeeder() {

        // Преобразуем список в фидер через встроенные методы
        return feed(listFeeder(ListMapFromAPI.getPlaySessionList()).circular());
// Если вы работаете с кастомными источниками данных,
// которые не поддерживаются напрямую в DSL (например, базой данных или файлами, созданными динамически),
// то подход с listFeeder() всё равно будет работать, если вы сначала преобразуете данные в List<Map<String, Object>>.
    }
}
