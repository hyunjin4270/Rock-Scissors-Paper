package main.session;

import main.domain.GameSession;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public class SessionManager {
    private final Map<String, GameSession> sessions = new HashMap<>();

    public String create(GameSession session, String id) {
        if (session == null) throw new IllegalArgumentException("session is null");
        sessions.put(id, session);
        return id;
    }

    public String createId() {
        return UUID.randomUUID().toString();
    }

    public GameSession get(String id) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (!sessions.containsKey(id)) throw new NoSuchElementException("not a valid id");
        return sessions.get(id);
    }

    public void remove(String id) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (!sessions.containsKey(id)) throw new NoSuchElementException("not a valid id");
        sessions.remove(id);
    }
}
