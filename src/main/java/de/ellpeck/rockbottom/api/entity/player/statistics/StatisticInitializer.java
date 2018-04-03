/*
 * This file ("StatisticInitializer.java") is part of the RockBottomAPI by Ellpeck.
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

package de.ellpeck.rockbottom.api.entity.player.statistics;

import de.ellpeck.rockbottom.api.RockBottomAPI;
import de.ellpeck.rockbottom.api.util.reg.IResourceName;

public abstract class StatisticInitializer<T extends Statistic>{

    private final IResourceName name;

    public StatisticInitializer(IResourceName name){
        this.name = name;
    }

    public IResourceName getName(){
        return this.name;
    }

    public abstract T makeStatistic(IStatistics statistics);

    public StatisticInitializer<T> register(){
        RockBottomAPI.STATISTICS_REGISTRY.register(this.getName(), this);
        return this;
    }

}