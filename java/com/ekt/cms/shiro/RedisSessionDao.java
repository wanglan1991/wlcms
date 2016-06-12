package com.ekt.cms.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sxjun on 15-9-8.
 */
public class RedisSessionDao implements SessionDAO {

    private static final Logger logger=Logger.getLogger(RedisSessionDao.class);

    private SessionIdGenerator sessionIdGenerator;

    private String activeSessionsCacheName = "redis-session-cacheName";


    private RedisManager redisManager;

    private int dbIndex = 1;

    @Override
    public Serializable create(Session session) {
        Serializable sessionId = session.getId();
        if (sessionId == null) {
            sessionId = sessionIdGenerator.generateId(session);
        }
        sessionId = activeSessionsCacheName + sessionId;
        SimpleSession simpleSession = (SimpleSession) session;
        simpleSession.setId(sessionId);
        try {
            redisManager.set(sessionId, simpleSession,dbIndex);
        } catch (IOException e) {
           logger.error("Error:",e);
        }
        return sessionId;
    }

    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        Object o = null;
        try {
            o = redisManager.get(sessionId,dbIndex);
        } catch (IOException e) {
            logger.error("Error:", e);
        } catch (ClassNotFoundException e) {
            logger.error("Error:", e);
        }
        if (o == null) {
            return null;
        }
        return (Session) o;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        Serializable sessionId = session.getId();
        if (sessionId == null) {
            return;
        }
        SimpleSession simpleSession = (SimpleSession) session;
        simpleSession.setId(sessionId);
        try {
            redisManager.set(sessionId, simpleSession,dbIndex);
        } catch (IOException e) {
            logger.error("Error:", e);
        }
    }

    @Override
    public void delete(Session session) {
        Serializable sessionId = session.getId();
        sessionId = activeSessionsCacheName + sessionId;
        try {
            redisManager.del(sessionId,dbIndex);
        } catch (IOException e) {
            logger.error("Error:", e);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Collection<Session> sessionCollection = new HashSet<>();
        try {
            Set<byte[]> fuzzyKeys = redisManager.getFuzzyKeys(activeSessionsCacheName + "*",dbIndex);
            if (fuzzyKeys == null) {
                return Collections.EMPTY_SET;
            }
            for (byte[] key : fuzzyKeys) {
                Object sessionVal = redisManager.get(key,dbIndex);
                Session session = (Session) sessionVal;
                sessionCollection.add(session);
            }
        }catch (Exception e){
            logger.error("Error:",e);
        }
        return sessionCollection;
    }

    public SessionIdGenerator getSessionIdGenerator() {
        return sessionIdGenerator;
    }

    public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        this.sessionIdGenerator = sessionIdGenerator;
    }

    public String getActiveSessionsCacheName() {
        return activeSessionsCacheName;
    }

    public void setActiveSessionsCacheName(String activeSessionsCacheName) {
        this.activeSessionsCacheName = activeSessionsCacheName;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }
}
