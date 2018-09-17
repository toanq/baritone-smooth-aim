/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.api.event.events;

import baritone.api.event.events.type.EventState;

public final class TickEvent {

    private final EventState state;
    private final Type type;
    private final int count;

    private static int overallTickCount;

    public TickEvent(EventState state, Type type) {
        this.state = state;
        this.type = type;
        this.count = incrementCount();
    }

    private static synchronized int incrementCount() {
        return overallTickCount++;
    }

    public int getCount() {
        return count;
    }

    public Type getType() {
        return type;
    }

    public EventState getState() {
        return state;
    }


    public enum Type {
        /**
         * When guarantees can be made about
         * the game state and in-game variables.
         */
        IN,
        /**
         * No guarantees can be made about the game state.
         * This probably means we are at the main menu.
         */
        OUT,
    }
}
