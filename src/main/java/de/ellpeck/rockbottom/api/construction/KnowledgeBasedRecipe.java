/*
 * This file ("KnowledgeBasedRecipe.java") is part of the RockBottomAPI by Ellpeck.
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

package de.ellpeck.rockbottom.api.construction;

import de.ellpeck.rockbottom.api.construction.resource.IUseInfo;
import de.ellpeck.rockbottom.api.entity.player.AbstractEntityPlayer;
import de.ellpeck.rockbottom.api.item.ItemInstance;
import de.ellpeck.rockbottom.api.util.reg.ResourceName;

import java.util.List;

public class KnowledgeBasedRecipe extends BasicRecipe {

    public KnowledgeBasedRecipe(ResourceName name, List<IUseInfo> inputs, List<ItemInstance> outputs, float skillReward) {
        super(name, inputs, outputs, skillReward);
    }

    public KnowledgeBasedRecipe(ResourceName name, float skillReward, ItemInstance output, IUseInfo... inputs) {
        super(name, skillReward, output, inputs);
    }

    public KnowledgeBasedRecipe(float skillReward, ItemInstance output, IUseInfo... inputs) {
        super(skillReward, output, inputs);
    }

    @Override
    public boolean isKnown(AbstractEntityPlayer player) {
        return !player.world.isStoryMode() || player.getKnowledge().knowsRecipe(this);
    }
}
