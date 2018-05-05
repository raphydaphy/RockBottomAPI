/*
 * This file ("MovableWorldObject.java") is part of the RockBottomAPI by Ellpeck.
 * View the source code at <https://github.com/RockBottomGame/>.
 * View information on the project at <https://rockbottom.ellpeck.de/>.
 *
 * The RockBottomAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The RockBottomAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the RockBottomAPI. If not, see <http://www.gnu.org/licenses/>.
 *
 * © 2017 Ellpeck
 */

package de.ellpeck.rockbottom.api.entity;

import de.ellpeck.rockbottom.api.RockBottomAPI;
import de.ellpeck.rockbottom.api.tile.state.TileState;
import de.ellpeck.rockbottom.api.util.ApiInternal;
import de.ellpeck.rockbottom.api.util.BoundBox;
import de.ellpeck.rockbottom.api.world.IWorld;
import de.ellpeck.rockbottom.api.world.layer.TileLayer;

import java.util.List;

public abstract class MovableWorldObject{

    public IWorld world;

    public double x;
    public double y;

    public double motionX;
    public double motionY;

    public final BoundBox currentBounds = new BoundBox();
    public final BoundBox currentBoundsWithMotion = new BoundBox();

    public boolean collidedHor;
    public boolean collidedVert;
    public boolean onGround;

    public MovableWorldObject(IWorld world){
        this.world = world;
        this.setPos(0D, 0D);
    }

    public void setPos(double x, double y){
        this.x = x;
        this.y = y;

        this.currentBounds.set(this.x, this.y, this.x, this.y).expand(this.getWidth()/2D, this.getHeight()/2D);
        this.currentBoundsWithMotion.set(this.currentBounds).add(this.motionX, this.motionY);
    }

    @ApiInternal
    public void move(){
        RockBottomAPI.getInternalHooks().doWorldObjectMovement(this);
    }

    @ApiInternal
    public void onTileCollision(int x, int y, TileLayer layer, TileState state, BoundBox objBox, BoundBox objBoxMotion, List<BoundBox> boxes){

    }

    @ApiInternal
    public void onEntityCollision(Entity entity, BoundBox thisBox, BoundBox thisBoxMotion, BoundBox otherBox, BoundBox otherBoxMotion){

    }

    @ApiInternal
    public void onTileIntersection(int x, int y, TileLayer layer, TileState state, BoundBox objBox, BoundBox objBoxMotion, List<BoundBox> boxes){

    }

    @ApiInternal
    public void onEntityIntersection(Entity entity, BoundBox thisBox, BoundBox thisBoxMotion, BoundBox otherBox, BoundBox otherBoxMotion){

    }

    public boolean canCollideWithTile(TileState state, int x, int y, TileLayer layer){
        return true;
    }

    public abstract float getWidth();

    public abstract float getHeight();
}
