/**
 * project : flashpush-server
 * user created : pippo
 * date created : 2009-8-13 - 下午12:37:45
 */
package com.rensea.message.server;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @author pippo
 * @since 2009-8-13
 */
@Service("authenticationManager")
public class AuthenticationManager {

    private Map<String, String> tickets = new ConcurrentHashMap<String, String>();

    public String applyTicket(String userId) {
        final String ticket = UUID.randomUUID().toString();
        synchronized (this.tickets) {
            this.tickets.put(ticket, userId);
        }
        /* 2分钟后,清除未来验证的ticket */
        this.scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                removeTicket(ticket);
            }
        }, 120, TimeUnit.SECONDS);
        return ticket;
    }

    public String verify(String ticket) {
        String userId = null;
        if (this.tickets.containsKey(ticket)) {
            userId = this.tickets.get(ticket);
            this.tickets.remove(ticket);
        }
        return userId;
    }

    protected void removeTicket(String ticket) {
        if (this.tickets.containsKey(ticket)) {
            this.tickets.remove(ticket);
        }
    }

    @Resource
    private ScheduledExecutorService scheduledExecutorService;

}
