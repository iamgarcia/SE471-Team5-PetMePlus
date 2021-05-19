package com.team5.petmeplus.util;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class ObjectPool<T> {
    private final long expireTime;
    private final Hashtable<T, Long> locked;
    private final Hashtable<T, Long> unlocked;

    public ObjectPool() {
        expireTime = 45000; // 45 seconds
        locked = new Hashtable<>();
        unlocked = new Hashtable<>();
    }

    protected abstract T create();

    public abstract boolean validate(T o);

    public abstract void expire(T o);

    public synchronized T checkoutObject() {
        long now = System.currentTimeMillis();
        T t;

        if (unlocked.size() > 0) {
            Enumeration<T> e = unlocked.keys();

            while (e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - unlocked.get(t)) > expireTime) {
                    // Object expired
                    unlocked.remove(t);
                    expire(t);
                    t = null;
                } else {
                    if (validate(t)) {
                        unlocked.remove(t);
                        locked.put(t, now);
                        return t;
                    } else {
                        // Object failed validation
                        unlocked.remove(t);
                        expire(t);
                        t = null;
                    }
                }
            }
        }

        // Create new object because there are none currently available
        t = create();
        locked.put(t, now);
        return t;
    }

    public synchronized void checkinObject(T t) {
        locked.remove(t);
        unlocked.put(t, System.currentTimeMillis());
    }
}
