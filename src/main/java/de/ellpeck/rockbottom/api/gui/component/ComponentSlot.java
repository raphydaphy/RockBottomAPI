/*
 * This file ("ComponentSlot.java") is part of the RockBottomAPI by Ellpeck.
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
 * © 2018 Ellpeck
 */

package de.ellpeck.rockbottom.api.gui.component;

import de.ellpeck.rockbottom.api.IGameInstance;
import de.ellpeck.rockbottom.api.IRenderer;
import de.ellpeck.rockbottom.api.data.settings.Settings;
import de.ellpeck.rockbottom.api.RockBottomAPI;
import de.ellpeck.rockbottom.api.assets.IAssetManager;
import de.ellpeck.rockbottom.api.gui.GuiContainer;
import de.ellpeck.rockbottom.api.gui.container.ContainerSlot;
import de.ellpeck.rockbottom.api.item.ItemInstance;
import de.ellpeck.rockbottom.api.util.reg.ResourceName;
import org.lwjgl.glfw.GLFW;

public class ComponentSlot extends GuiComponent {

    public final ContainerSlot slot;
    public final int componentId;
    public final GuiContainer container;

    private boolean renderBackground = true;
    private boolean useColorOverride = false;
    private int colorOverride = -1;

    public ComponentSlot(GuiContainer container, ContainerSlot slot, int componentId, int x, int y) {
        super(container, x, y, 16, 16);
        this.container = container;
        this.slot = slot;
        this.componentId = componentId;
    }

    /**
     * This disables the background rendering of the slot. Your slot will be
     * invisible with this enabled if there are no items in it. You should
     * probably only use this if your GUI texture includes its own slot markers.
     *
     * @return This component slot
     */
    public ComponentSlot disableBackgroundRender() {
        renderBackground = false;
        return this;
    }

    /**
     * This forces your slot to render as a specific color. By default, slots
     * will use the user-defined color preference {@link Settings#guiColor}
     *
     * @param color The color that you want your slot to use
     * @return The colored component slot. The same as this one
     */
    public ComponentSlot addColorOverride(int color) {
        this.useColorOverride = true;
        this.colorOverride = color;
        return this;
    }

    @Override
    public boolean onMouseAction(IGameInstance game, int button, float x, float y) {
        if (this.isMouseOver(game)) {
            if (game.getInput().isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
                return RockBottomAPI.getInternalHooks().doDefaultShiftClicking(game, button, this.container, this);
            } else {
                return RockBottomAPI.getInternalHooks().doDefaultSlotMovement(game, button, x, y, this.container, this);
            }
        } else {
            return false;
        }
    }

    @Override
    public ResourceName getName() {
        return ResourceName.intern("slot");
    }

    @Override
    public void render(IGameInstance game, IAssetManager manager, IRenderer g, int x, int y) {
        ItemInstance holding = this.container.getContainer().holdingInst;
        g.renderSlotInGui(game, manager, this.slot.get(), x, y, 1F, this.isMouseOver(game), holding == null || this.slot.canPlace(holding), renderBackground, useColorOverride ? colorOverride : getGuiColor());
    }

    @Override
    public void renderOverlay(IGameInstance game, IAssetManager manager, IRenderer g, int x, int y) {
        if (this.container.getContainer().holdingInst == null && this.isMouseOverPrioritized(game)) {
            ItemInstance instance = this.slot.get();
            if (instance != null) {
                g.describeItem(game, manager, instance, true);
            }
        }
    }
}
