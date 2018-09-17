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

package baritone.pathing.movement;

import baritone.utils.InputOverrideHandler.Input;
import baritone.utils.Rotation;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MovementState {

    private MovementStatus status;
    private MovementTarget goal = new MovementTarget();
    private MovementTarget target = new MovementTarget();
    private final Map<Input, Boolean> inputState = new HashMap<>();

    public MovementState setStatus(MovementStatus status) {
        this.status = status;
        return this;
    }

    public MovementStatus getStatus() {
        return status;
    }

    public MovementTarget getGoal() {
        return this.goal;
    }

    public MovementState setGoal(MovementTarget goal) {
        this.goal = goal;
        return this;
    }

    public MovementTarget getTarget() {
        return this.target;
    }

    public MovementState setTarget(MovementTarget target) {
        this.target = target;
        return this;
    }

    public MovementState setInput(Input input, boolean forced) {
        this.inputState.put(input, forced);
        return this;
    }

    public boolean getInput(Input input) {
        return this.inputState.getOrDefault(input, false);
    }

    public Map<Input, Boolean> getInputStates() {
        return this.inputState;
    }

    public enum MovementStatus {
        PREPPING, WAITING, RUNNING, SUCCESS, UNREACHABLE, FAILED, CANCELED
    }

    public static class MovementTarget {

        /**
         * Necessary movement to achieve
         * <p>
         * TODO: Decide desiredMovement type
         */
        public Vec3d position;

        /**
         * Yaw and pitch angles that must be matched
         * <p>
         * getFirst()  -> YAW
         * getSecond() -> PITCH
         */
        public Rotation rotation;

        /**
         * Whether or not this target must force rotations.
         * <p>
         * {@code true} if we're trying to place or break blocks, {@code false} if we're trying to look at the movement location
         */
        private boolean forceRotations;

        public MovementTarget() {
            this(null, null, false);
        }

        public MovementTarget(Vec3d position) {
            this(position, null, false);
        }

        public MovementTarget(Rotation rotation, boolean forceRotations) {
            this(null, rotation, forceRotations);
        }

        public MovementTarget(Vec3d position, Rotation rotation, boolean forceRotations) {
            this.position = position;
            this.rotation = rotation;
            this.forceRotations = forceRotations;
        }

        public final Optional<Vec3d> getPosition() {
            return Optional.ofNullable(this.position);
        }

        public final Optional<Rotation> getRotation() {
            return Optional.ofNullable(this.rotation);
        }

        public boolean hasToForceRotations() {
            return this.forceRotations;
        }
    }
}
