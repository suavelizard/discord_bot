/*
 * Discord4J - Unofficial wrapper for Discord API
 * Copyright (c) 2015
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package sx.blah.discord.handle.impl.events;

import sx.blah.discord.handle.IEvent;
import sx.blah.discord.handle.obj.Channel;
import sx.blah.discord.handle.obj.User;

/**
 * @author qt
 * @since 1:39 AM, 10/17/15
 * Project: Discord4J
 */
public class TypingEvent implements IEvent {
    private final User user;
    private final Channel channel;

    public TypingEvent(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }

    public User getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }
}
